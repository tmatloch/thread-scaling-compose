/*
 * Copyright 2011-2019 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package event

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SimulationRamp extends Simulation {

  val fastUsersNo = Integer.getInteger("fastUsersNo", 5);
  val slowUsersNo = Integer.getInteger("slowUsersNo", 5);
  val duration = Integer.getInteger("duration", 5);

  val httpProtocol = http
    .baseUrl("http://producer:9083") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")


  val fast = scenario("fast")
	.exec(http("request_f")
      .post("/api/v1.0/event/fast?text=permutes&multiply=5"))
  val slow = scenario("slow")
	  .exec(http("request_s")
      .post("/api/v1.0/event/slow?text=permutes&multiply=10"))
  setUp(
    fast.inject(rampConcurrentUsers(0) to(fastUsersNo) during (duration seconds)).protocols(httpProtocol),
    slow.inject(rampConcurrentUsers(0) to(slowUsersNo) during (duration seconds)).protocols(httpProtocol))
}
