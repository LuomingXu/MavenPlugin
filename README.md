maven-plugin
=

[![image](https://img.shields.io/badge/maven-v0.1-blue.svg)](https://search.maven.org/search?q=g:com.github.luomingxuorg)
[![image](https://img.shields.io/badge/License-Apache__v2-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)


    self use maven plugin
    
Current Feature
-

- 在window执行<font color=#ff0000>powershell</font>的代码, 支持多行, 可以利用此, 来实现一键部署
    - 为了说不定会出现的编码问题提供了输出编码的调整

GetStart
-

```xml
<!--in pom.xml-->
<build>
    <plugins>
        <plugin>
            <groupId>com.github.luomingxuorg</groupId>
            <artifactId>luomingxu-maven-plugin</artifactId>
            <version>${version}</version>
            <configuration>
                <cmds>
                    <cmd>${your command}</cmd>
                </cmds>
                <charset>${default is UTF-8}</charset>
                <!--if you see some "$%@#%^^*", you may need to change the charset-->
            </configuration>
        </plugin>
        <!--other plugin-->
    </plugins>
</build>
```