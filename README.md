# Simple Archive

A simple archive management system for those who:

- Already have a running file server / object storage service
- Don't mind restarting the service and causing downtime
- Just need a simple web interface to organize and share their file URLs

## Requirement

- Java 17 +

## Build

```shell
mvn package
```

## Usage

### Setup `application.yml`

An example for `application.yml` looks like this:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:sqlite:<path_to_db_file> # Simple Archive will create and initiate the DB file on the first startup
    driver-class-name: org.sqlite.JDBC

simple-archive:
  admin-account:
    username: <admin_username>
    password: <admin_password> # In plaintext
```

### Run Simple Archive

```shell
java -jar simple-archive.jar --spring.config.location=<path_to_application.yml>
```

### Browse an archive

Visit `http://<your_domain>:<your_port>/archive/<archive_slug>` to browse a particular archive.

The slug is an editable part of the URL that identifies an archive.

### Manage archives

Visit `http://<your_domain>:<your_port>/admin` to log in to the dashboard, where archives can be created, edited, and deleted.

### What makes a "Simple Archive" archive

An archive contains following fields:

- Title: title of the archive
- Slug: a short text that identifies the archive
- Status: visibility of the archive
  - `active`: available for any guest
  - `hidden`: only available in admin view
- Description: an optional text that provides more information about the archive
- File: what really matters
  - File name: well, the name of the file
  - File URL: the URL to the file, usually leads to a file server or object storage service