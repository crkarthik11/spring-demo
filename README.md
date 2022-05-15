# spring-demo

## 1. How to start
```
$ git clone <url>
$ cd springdemo
$ mvnw spring-boot:run
$ curl -v localhost:8080
```
## RequestParam URL format
```
http://localhost:8080/user/101?param1=10&param2=20
```
## Swagger UI
```
http://localhost:8080/swagger-ui.html#/
```
## VSCode Git Cheatsheet
Git help command
```
git --help
```
Check repository status
```
git status
```
Initialize repository
```
git init
```
Add all updated changes to staging area (Let Git track all files in the repository)
```
git add --all
```
Commit changes
```
git commit -m "commit message"
```
Push changes to master branch
```
git push -u origin master
```
Pull from master branch to local repository
```
git pull origin master
```
# Spring Data JPA Query Methods Example
### JPA Query
```java
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT student FROM Student student WHERE student.studentDepartment = : DEPT")
    Optional<List<Student>> getStudentByDepartment(String DEPT);
}
```
### Native Query
```java
public interface AdminRepository extends JpaRepository<Admin,Long>{
    
    List<Optional<Admin>> findByAdminName(String adminName);

    Optional<Admin> findByAdminUsername(String adminUsername);

    List<Optional<Admin>> findByAdminUsernameAndAdminPassword(String adminUsername, String adminPassword);

    @Query(value = "SELECT * FROM tbl_admin WHERE AGE >= ?1 AND AGE <= ?2", nativeQuery = true)
    List<Optional<Admin>> findByAgeBetween(int startAge, int endAge);
}
```