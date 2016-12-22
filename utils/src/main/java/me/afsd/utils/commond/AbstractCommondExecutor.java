package me.afsd.utils.commond;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XuHui (416422546@qq.com)
 * @version 0.0.1
 * @date 2016/12/22
 */
public abstract class AbstractCommondExecutor {
    private Map<String, Method> methodMap = new HashMap<>();
    private String lastCommondLine = "";

    @Command(command = "-help", desc = "获取帮助信息", rangArgs = true)
    public void help(String[] commands) {
        if (commands.length == 0) {
            methodMap.keySet().stream().sorted().forEach(key -> {
                Command command = methodMap.get(key).getAnnotation(Command.class);
                System.out.printf("%-12s: %s \n", key, command.desc());
            });
        } else {
            if (methodMap.containsKey(commands[0])) {
                Command command = methodMap.get(commands[0]).getAnnotation(Command.class);
                System.out.printf("%-12s: %s \n", command.command(), command.desc());
            } else {
                System.out.println("方法不存在");
            }
        }
    }

    @Command(command = "-q", desc = "退出")
    public void exit() {
        System.exit(0);
    }

    public AbstractCommondExecutor() {
        for (Method method : this.getClass().getMethods()) {
            if (method.getAnnotation(Command.class) != null) {
                Command command = method.getAnnotation(Command.class);
                System.out.println(command.command());
                method.setAccessible(true);
                methodMap.put(command.command(), method);
            }
        }
    }

    protected void execute(String commondLine) throws InvocationTargetException, IllegalAccessException {
        if (commondLine == null || commondLine.isEmpty())
            commondLine = lastCommondLine;
        String[] args = commondLine.split(" ");
        if (!methodMap.containsKey(args[0])) {
            System.out.println("命令不存在");
            return;
        }
        Method method = methodMap.get(args[0]);
        Command command = method.getAnnotation(Command.class);
        if (!command.rangArgs() && args.length - method.getParameterCount() != 1) {
            System.out.println("参数错d误");
            return;
        }
        if (method.getParameterCount() == 0) {
            method.invoke(this);
        } else {
            String[] realArgs = Arrays.copyOfRange(args, 1, args.length);
            if (command.rangArgs()) {
                Object[] objectArg = {realArgs};
                method.invoke(this, objectArg);
            } else {
                method.invoke(this, (Object[]) Arrays.copyOfRange(args, 1, args.length));
            }
        }
    }

    public void start() {
        Long startTime;
        Long endTime;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.print(">");
        while (true) {
            try {
                line = bf.readLine();
                startTime = System.currentTimeMillis();
                execute(line);
                endTime = System.currentTimeMillis();
                System.out.println("耗时：" + (endTime - startTime));
            } catch (IOException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.print(">");
        }
    }
}
