[phantomjs]: http://phantomjs.org/download.html
[maven]: http://maven.apache.org/
[git]: http://git-scm.com/

## Allure Yandex Test

### Testing task for SBT

To run tests [maven][maven], [git][git] and [phantomjs][phantomjs] are required

To generate Allure Report:

```bash
$ git clone git@github.com:sergusp/allure-yandex-test.git
$ mvn clean test
$ mvn site
```

To see a report: `mvn jetty:run` or `mvn jetty:run -Dorg.eclipse.jetty.annotations.maxWait=120` (in case of timeout error)
 and open `http://localhost:8080` in browser


### References

* Examples from: http://allure.qatools.ru/