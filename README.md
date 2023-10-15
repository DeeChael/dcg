# (WIP) Dynamic Class Generator
### Allowing you to generate classes while running, or recompile classes, easier than using byte codes, and more!

## Usage

```java
DynamicClassManager manager = DynamicClassManager.builder()
        // do sth to modify the settings
        .build()

DynamicSourcer sourcer = manager.sourcer();

DyClass newClazz = sourcer.newClass()
        .withPackage("net.deechael.dcg.test")
        .withName("Test")
        .withVisibility(Visibility.PUBLIC)
        .build();

// do other things
```