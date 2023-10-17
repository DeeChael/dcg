# (WIP) Dynamic Class Generator
### Allowing you to generate classes while running, or recompile classes, easier than using byte codes, and more!

## Usage

```java
DynamicClassManager manager = DynamicClassManager.builder()
        // do sth to modify the settings
        .build()

DynamicSourcer sourcer = manager.sourcer();

DyPackage pkg = sourcer.newPackage("net.deechael.dcg.test");
DyClass clazz = pkg.newClass(Visibility.PUBLIC, "Test");

// do other things
```