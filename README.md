

# IMPC PROJECT

> ### About:
>
> ----

This project provides RESTful API service that allows other applications to consume and interact with the data in the IMPC (**International Mouse Phenotyping Consortium**) reference database.

> ### Tech Stack:
>
> ----

Spring Boot

Java 8

PostgreSQL

> ### Api:
>
> ---

This project provides 2 Application Programming Interfaces to interact with the IMPC reference database. 

##### 1. Main api : http://localhost:yourPort/api/impc/mouse-gene/search?key=searchkey

> ###### About: 
>
> - This api is used to fetch list of Mouse Gene **Symbols and Synonyms** along with its associated list of **Human Gene's Support Count** `*[search key is optional]*`.
> - If searched without a key, the api will list all the Mouse Gene Symbols and Synonyms along with its associated list of Human Gene's Support Count
>
> ###### List of search scenarios included in the api:
>
> - Search by complete/partial mouse gene ‘symbol’ (e.g. Fgf8 or FG) [***case insensitive***]
> - Search by complete/partial mouse gene 'synonym' (e.g. Aigf or Aig) [***case insensitive***]
> - Search by complete/partial mgi_gene_acc_id (e.g. MGI:99604 or mgi:996) [***case insensitive***]
>
> ###### Sample Output for search key 'Fgf8' :
>
> ```` json
> {
>     "responseCode": 200,
>     "message": "OK",
>     "status": "SUCCESS",
>     "data": [
>         {
>             "symbol": "Fgf8",
>             "name": "fibroblast growth factor 8",
>             "synonyms": [
>                 "Fgf-8",
>                 "Aigf"
>             ],
>             "humanGeneResources": [
>                 {
>                     "id": 9024,
>                     "supportCount": 12
>                 }
>             ]
>         }
>     ]
> }
> ````

##### 2. Additional api : http://localhost:yourPort/api/impc/human-gene/supportCount?key=searchkey

> ###### About: 
>
> - This Additional api is to separately fetch list of Human Genes Support Count associated with a specified mouse gene searchable via **mouse gene symbol or mgi_gene_account_id** `*[search key is madantory]*`
>
> ###### List of search scenarios included in the api:
>
> - Search by complete/partial mouse gene ‘symbol’ (e.g. Fgf8 or FG) [***case insensitive***]
>
> - Search by complete/partial mgi_gene_acc_id (e.g. MGI:99604 or mgi:996) [***case insensitive***]
>
> ##### Sample Output for search key 'MGI:99604' :
>
> ```` json
> {
>     "responseCode": 200,
>     "message": "OK",
>     "status": "SUCCESS",
>     "data": [
>         {
>             "id": 9024,
>             "supportCount": 12
>         }
>     ]
> }
> ````

> ### Steps to run:
>
> ---

**1. Clone the repository**:

```
git clone https://github.com/Madhujp98/IMPCProject.git
```

**2. Configure PostgreSQL and essential application properties**:

- This project uses default database '**postgres**'  with tables inside its '**public**' schema. 
- Open `src/main/resources/application.properties` file and change the spring datasource url, username and password as per your PostgreSQL configurations.
- Change server port in`src/main/resources/application.properties`  if required.

**3. Run the app and hit the above mentioned two apis (`1. http://localhost:yourPort/api/impc/mouse-gene/search?key=searchkey` `2. http://localhost:yourPort/api/impc/mouse-gene/search?key=searchkey`) from postman/similar to test the results**:

Type the following command from the root directory of the project to run the application:

```
mvn spring-boot:run
```

