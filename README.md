# Apache Jena Fuseki API

## 1. 설명
Apache Jena Fuseki API이다.

## 2. 개발환경

* OpenJDK 21

* Apache Jena Fuseki 6.0.0

## 3. API

### 1) Delete

* 조건부 삭제 (SPARQL DELETE): 특정 패턴에 맞는 데이터를 삭제할 때 사용한다.

* 그래프 전체 삭제 (GSP DELETE): 쿼리문 작성 없이 HTTP DELETE 메서드를 사용하여 특정 그래프(또는 기본 그래프)의 모든 데이터를 한 번에 삭제하는 방식이다.

### 2) ExportData
Dataset Store Protocol을 사용하여 데이터셋 전체(기본 그래프 + 모든 네임드 그래프)을 export 한다.

### 3) Indexer
온톨로지 파일(OWL, RDF, TTL 등)을 색인(Index)하거나 서버에 업로드(Insert)하는 방법이 있다.

Jena의 DatasetAccessor(GSP: Graph Store Protocol)를 사용하는 방식과 SPARQL UPDATE 쿼리를 사용하는 방식이 있다.

### 4) ParameterizedSparql
SQL의 `PreparedStatement`와 유사하게, 쿼리 템플릿에 변수를 안전하게 주입하여 SPARQL Injection 공격을 방지하고, URI나 리터럴의 복잡한 구문(따옴표 처리 등)을 라이브러리가 자동으로 해결해 준다.

### 5) Select
`QueryExecution.service()` 빌더 형식을 사용한다.

### 6) Update
데이터를 수정(INSERT, DELETE, UPDATE)할 때 사용하는 API는 `UpdateExecution`이다. `QueryExecution`과 마찬가지로 빌더(Builder) 패턴을 사용하여 직관적이고 표준적인 HTTP 통신을 지원한다.
