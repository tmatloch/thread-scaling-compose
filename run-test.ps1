param(
    [double]$slowScale = 0.5,
    [double]$fastScale = 0.5,
    [int]$fastUsersNo = 5,
    [int]$slowUsersNo = 5,
    [int]$duration = 5
)

$json = @"
{
     "fast": "$fastScale",
     "slow": "$slowScale"
}
"@
$responsedata = Invoke-RestMethod -Uri http://localhost:8088/queue-scale -Method POST -Body $json -ContentType 'application/json'
echo $responsedata

docker run -it --rm -v ${PWD}/gatling/user-files:/opt/gatling/user-files `
  -v ${PWD}/gatling/results:/opt/gatling/results `
  --network scaling-compose_scaling_compose `
  -e JAVA_OPTS="-DfastUsersNo=$fastUsersNo -DslowUsersNo=$slowUsersNo -Dduration=$duration" `
  denvazh/gatling

echo "END"