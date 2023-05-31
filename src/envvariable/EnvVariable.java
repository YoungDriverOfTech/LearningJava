package envvariable;

public class EnvVariable {
    /**
     * 配置环境变量的步骤
     * 1. 在~/.bash_profile 中增加环境变量
     *  - export TOMCAT_HOME=/xx/xxx/xx/tomcat
     *  - export PATH=$PATH:$TOMCAT_HOME/bin
     * 2. 执行命令，让环境变量生效  source ~/.bash_profile
     * 3. 在~/.zshrc文件后面，增加一行 source ~/.bash_profile (解决电脑重启后，环境变量失效的问题)
     */
}
