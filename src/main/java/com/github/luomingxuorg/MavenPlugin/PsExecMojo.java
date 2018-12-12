/*
 *  Copyright 2018-2018 LuomingXuOrg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  Author : Luoming Xu
 *  File Name : PsExecMojo.java
 *  Url: https://github.com/LuomingXuOrg/MavenPlugin
 */

package com.github.luomingxuorg.MavenPlugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Mojo(name = "psExec")
public class PsExecMojo extends AbstractMojo
{
    @Parameter(property = "charset", defaultValue = "UTF-8")
    private String charset;

    @Parameter(property = "cmds", required = true)
    private String[] cmds;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException
    {
        List<String> commands = new ArrayList<>();
        commands.add("powershell");
        commands.add("-Command");
        commands.add("\"& { ");

        for (String cmd : cmds)
        {
            commands.add(cmd + ";");
        }

        commands.add("}\"");
        Runtime runtime = Runtime.getRuntime();
        String[] temp = new String[commands.size()];

        try
        {
            Process p = runtime.exec(commands.toArray(temp));

            InputStream inputStream = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(charset)));

            String lineStr;
            while ((lineStr = reader.readLine()) != null)
            {
                System.out.println(lineStr);
            }
        }
        catch (Exception e)
        {
            System.out.println("error: " + e);
        }
    }
}
