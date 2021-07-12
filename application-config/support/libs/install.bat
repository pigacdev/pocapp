:: Za svaki jar koji želimo instalirati u repozitorij projekta, kreirati redak po uzoru na ovaj:

call mvn install:install-file -Dfile=kontrolniAlgoritmi.jar -DgroupId=hr.fina.common -DartifactId=kontrolniAlgoritmi -Dversion=1.0 -Dpackaging=jar -DlocalRepositoryPath=../repo