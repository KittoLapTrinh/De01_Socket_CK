# De01_Socket_CK
# Class diagram
![image](https://github.com/KittoLapTrinh/De01_Socket_CK/assets/96908923/1976ee04-6bd0-4f30-9db1-d2ca5a83f9ba)

# Data Diagram
![image](https://github.com/KittoLapTrinh/De01_Socket_CK/assets/96908923/ab69e60e-c1fc-4659-8676-c1bd5f558e9a)

# Question
![image](https://github.com/KittoLapTrinh/De01_Socket_CK/assets/96908923/65fdfd56-142e-4664-b1dd-737ad042e9e3)

# Setup Persistence.xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
	 <persistence-unit name="jpa-mssql">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="com.microsoft.sqlserver.jdbc.SQLServerDriver"/>
            <property name="jakarta.persistence.jdbc.dialect" value="org.hibernate.dialect.SQLServerDialect"/>
            <property name="hibernate.connection.url" value="jdbc:sqlserver://localhost:1433;databaseName=de01_socket_ck;trustServerCertificate=true;encrypt=true;"/>
            <property name="hibernate.connection.username" value="sa"/>
            <property name="hibernate.connection.password" value="sapassword"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>

