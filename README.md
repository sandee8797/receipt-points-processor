# receipt-points-processor
A Spring Boot REST API to process receipts and calculate points based on predefined rules. It provides endpoints to submit receipts and retrieve points. The application uses in-memory storage for simplicity and includes Docker support for seamless deployment and scalability.

API Endpoints
1. Process Receipt
Path: /receipts/process
Method: POST
Description: Submit a receipt and get a unique ID

Example Request:
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    { "shortDescription": "Mountain Dew 12PK", "price": "6.49" }
  ],
  "total": "6.49"
}


Example Response:
API Endpoints
1. Process Receipt
Path: /receipts/process
Method: POST
Description: Submit a receipt and get a unique ID





2. Get Points
Path: /receipts/{id}/points
Method: GET
Description: Retrieve points for a specific receipt.

Example Response:

{ "points": 32 }




How to Run
Using Docker

1.Build the Docker image:
docker build -t receipt-processor .

2.Run the container:
docker run -p 8080:8080 receipt-processor


Running Locally

1.Build the project:
mvn clean package

2.Run the application:
java -jar target/receipt-processor.jar



