jConsol
=======
Simple command-to-method binder.

### Usage
Create a class with some methods:

```java
public static class Math {
    public void multiply(int p1, int p2){
        System.out.println(p1 * p2);
    }
}    
```

Annotate it:

```java
@CommandClass(prefix = "do")
public static class Math {
    @Command(
        command = "multiply",
        params = {Int.class, Int.class}        
    )
    public void command(int p1, int p2){
        System.out.println(p1 * p2);
    }
}    
```

Add it to your command manager:

```java
CommandManager man = new CommandManager();
man.register(Math.class);
```

And feed it with commands:

```java
man.run("do multiply 12 42");
```
### Post scriptum

You can run commands by calling ```prefix command param1 param2 "param 3"```. 

You can add multiple commands to class and multiple classes to the manager. 

Prefixes with similar names will be merged.

Case is ignored in prefix and command names.

If CommandClass has empty prefix then all commands of that class can be used without prefix. 
In that case while resolving command and prefix with same name, prefix will have a higher priority.
                                                                                                                  
If you want to include spaces in parameter, when wrap parameter with quotes. Quote can be escaped with ```\"```. 