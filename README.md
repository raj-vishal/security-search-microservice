# -security-search-microservice
MicroService that provides Spring Security and lists the top occurances of text or words

The Microservice is exposed on PORT 9004, as is defined in the application.properties file.
Additionally Basic Authorization has been implemented using Spring-Boot_Security, wherein the credentials given are BASE64 encoded.If the given credentials do not match the stored credentials, authorization is restricted.
Task 1: The following curl would return a JSON, that determinedd the count of the keywords sent in the request JSON 

curl http://localhost:9007/counter-api/search -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -d '{"searchText":["Duis", "Sed", "Donec", "Argue", "Pellentesque", "123"]}' -H "Content-Type: application/json" -X POST

Eg: {"Sed":16,"Donec":8,"Augue":7,"Pellentesque":6,"Duis":11}

Task 2: Provide the top 20 (as Path Variable) Texts, which has the highest counts in the Sample Paragraphs provided.

curl http://localhost:9007/counter-api/top/20 -H "authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -H "Accept: text/csv"

Would result in the generation of a CSV file with the following content Eg: vel|17 eget|17 sed|16 in|15 et|14 ut|13 eu|13 id|12 ac|12 metus|12 sit|12 amet|12 nulla|12 at|11 ipsum|11 duis|11 nec|11 vitae|11 dolor|10 aliquam|10
## security-search-microservice
