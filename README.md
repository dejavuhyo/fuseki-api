# Apache Jena Fuseki API

## 1. 설명
Apache Jena Fuseki API이다.

## 2. 개발환경

* OpenJDK 21

* Apache Jena Fuseki 6.0.0

## 3. API

### 1) Delete
서버에 저장된 데이터를 삭제하는 기능을 수행한다.

첫 번째 방법은 주어, 서술어, 목적어가 일치하는 데이터만 찾아서 지운다.

두 번째 방법은 변수(?p, ?o)를 사용하여 특정 조건에 맞는 데이터를 일괄 삭제한다.

### 2) ExportData
Apache Jena의 TDB2(Triple Database 2) 스토리지에 저장된 데이터를 읽어서 백업 파일(.nq)로 추출(Export)하는 기능을 수행한다.

### 3) Indexer
로컬에 있는 온톨로지(Ontology) 파일을 그래프 데이터베이스에 업로드(색인)하는 기능을 수행한다.

### 4) ParameterizedSparql
ParameterizedSparqlString을 사용하여 보안과 효율성을 갖춘 동적 SPARQL 쿼리를 실행하는 기능을 수행한다.

### 5) Select
RDFConnection을 활용하여 쿼리 결과를 한 줄씩 실시간으로 처리(Streaming)하는 기능을 수행한다.

데이터를 하나씩 꺼내어 자바 로직으로 직접 핸들링하는 방식이다.

### 6) Update
RDFConnection을 사용하여 SPARQL Update 명령을 실행하는 기능을 수행한다.

특정 데이터(Triple)를 서버에 직접 하나씩 추가(Insert)하는 기능을 수행한다.
