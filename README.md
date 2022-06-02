# batch

spring batch demo project

1. Creating Batch Service : h2(Java 내부 in-memory db)를 이용
   https://spring.io/guides/gs/batch-processing/


* 구성
  * a reader, a processor, a writer -> Step -> Job 으로 구성된다.
    

```java
@Bean //Define the job
public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
  return jobBuilderFactory.get("importUserJob")
    .incrementer(new RunIdIncrementer())
    .listener(listener)
    .flow(step1)
    .end()
    .build();
}

@Bean //Define a single step
public Step step1(JdbcBatchItemWriter<Person> writer) {
  return stepBuilderFactory.get("step1")
    .<Person, Person> chunk(10)
    .reader(reader())
    .processor(processor())
    .writer(writer)
    .build();
}
```
* listener
    * job 의 상태에 따른 동작을 추가할 수 있다.
    
```java
@Override
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

```

2. Batch Service with MySQL - h2가 아닌 MySQL 을 사용
https://spring.io/guides/gs/accessing-data-mysql/
   
* 1번에서 h2 에 자동생성되는 metatable 을 직접 MySQL 에 생성
  https://velog.io/@ililil9482/Spring-Batch-Mysql-%EC%97%B0%EB%8F%99%EA%B3%BC-Meta-Table%EC%9D%98-%EA%B0%9C%EB%85%90