# HangMan

```
.__.
|  |
|
|
|
|
|  _____
```

A simple project for studying the Java language, using third-party libraries, and object-oriented programming.

## Compilation, packaging and use.

> **Observation**
>
> You must have [**Java**](https://jdk.java.net/) installed and [**Maven**](https://maven.apache.org/install) to install the libraries.

Clone the repository, and then use Maven to install the dependencies.

```shell
git clone https://github.com/Ahosall/HangMan.git
cd HangMan
mvn install
```

After finishing cloning and installing the dependencies, you can compile to run.

```shell
mvn clean package
```

And finally, just execute.

```shell
java -jar target/hangman-<version>.jar # Insert the version that is in pom.xml:10
```
