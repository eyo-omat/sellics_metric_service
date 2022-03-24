# sellics_metric_service


# Start Up
This solution was built in java 11, it is a maven project.
Update the below configurations in the application.properties file with your AWS access key and secret key before startup

`sellics.aws.access.key=`

`sellics.aws.secret.key=`

Run the below command to start up the project

`mvn spring-boot:run`

# API
Below are the available APIs for interacting with project

- _This API returns the individual ranks for an ASIN, for a certain keyword_

**Endpoint** -> -> GET http://localhost:8087/sellics_metrics/individual-ranks/{keyword}/{asin}

**Sample Request**

http://localhost:8087/sellics_metrics/individual-ranks/2012 f250 wheel hub/B07T6ZD26W

**Sample Response**

`[
{
"timestamp": "2021-11-14T01:09:00.000Z",
"asin": "B07T6ZD26W",
"keyword": "2012 f250 wheel hub",
"rank": 74
},
{
"timestamp": "2021-11-14T01:09:00.000Z",
"asin": "B07T6ZD26W",
"keyword": "2012 f250 wheel hub",
"rank": 74
}
]`

- _This API returns the aggregated ranks for a keyword_

**Endpoint** -> GET http://localhost:8087/sellics_metrics/aggregated-ranks-keyword/{keyword}

**Sample Request**

http://localhost:8087/sellics_metrics/aggregated-ranks-keyword/weight loss coffee

**Sample Response**

`{
"1": [
{
"timestamp": "2021-10-29T01:43:16.000Z",
"asin": "B07G1C9R5X",
"keyword": "weight loss coffee",
"rank": 1
},
{
"timestamp": "2021-10-31T01:42:55.000Z",
"asin": "B07G1C9R5X",
"keyword": "weight loss coffee",
"rank": 1
}
],
"2": [
{
"timestamp": "2021-10-29T01:43:16.000Z",
"asin": "B06XC5H82N",
"keyword": "weight loss coffee",
"rank": 2
},
{
"timestamp": "2021-10-31T01:42:55.000Z",
"asin": "B06XC5H82N",
"keyword": "weight loss coffee",
"rank": 2
}
]
}`

- _This API returns the aggregated ranks for a keyword_

**Endpoint** -> GET http://localhost:8087/sellics_metrics/aggregated-ranks-asin/{asin}

**Sample Request**

http://localhost:8087/sellics_metrics/aggregated-ranks-asin/B07KYFGPNW

**Sample Response**

`{
"151": [
{
"timestamp": "2021-11-16T01:08:58.000Z",
"asin": "B07KYFGPNW",
"keyword": "ice pack reusable",
"rank": 151
}
],
"88": [
{
"timestamp": "2021-10-25T01:06:30.000Z",
"asin": "B07KYFGPNW",
"keyword": "ice pack reusable",
"rank": 88
}
]
}`