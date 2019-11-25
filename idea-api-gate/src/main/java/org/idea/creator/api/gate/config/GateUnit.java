package org.idea.creator.api.gate.config;

import org.idea.creator.api.gate.IGateInterface;
import org.idea.creator.api.gate.annotation.Gate;
import org.idea.creator.api.gate.exception.GateException;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author lqh
 */

public class GateUnit {

    private static ApplicationContext appContext = null;

    private static Map<String, Class<?>> classHashMap = new HashMap<>();

    public static void setAppContext(ApplicationContext context){
        appContext = context;
    }

    public static void loadGateClass (List<String> classPaths) throws GateException{
        if(classPaths == null || classPaths.size() < 1 || !classHashMap.isEmpty()){
            return;
        }
        Set<Class<?>> classSet = new HashSet<>();
        for (String clazzPath : classPaths) {
            classSet.addAll(loadClassInPath(clazzPath));
        }
        for (Class<?> clazz : classSet) {
            Gate gate = clazz.getAnnotation(Gate.class);
            String value = gate.value();
            classHashMap.put(value, clazz);
        }
    }

    public static boolean checkGateEnable(Set<String> gateName, HttpServletRequest request, HttpServletResponse response, Object handler) throws GateException{
        for (String gate : gateName) {
            Class<?> clazz = classHashMap.get(gate);
            if(clazz == null || clazz.isAssignableFrom(IGateInterface.class)){
                throw new GateException("gate with name "+gate+" not exist");
            }
            IGateInterface gateInterface = (IGateInterface) appContext.getBean(clazz);
            if(!gateInterface.handler(request, response, handler)){
                throw new GateException("gate "+ gate +" valid failed!");
            }
        }
        return true;
    }

    private static Set<Class<?>> loadClassInPath(String clazzPath) throws GateException {
        try {
            Set<Class<?>> clazzSet = new HashSet<>();
            String clazzDir = clazzPath.replaceAll("\\.","/");
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(clazzDir);
            while (dirs.hasMoreElements()){
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if("file".equals(protocol)){
                    addFileUrl(clazzPath, url, clazzSet);
                }else if("jar".equals(protocol)){
                    loadJarClazz(clazzPath,url,clazzSet);
                }
            }
            return clazzSet;
        } catch (Exception e) {
            throw new GateException(e);
        }
    }

    private static void loadJarClazz(String clazzPath,URL url, Set<Class<?>> clazzSet) throws  GateException{
        try{
            JarFile jarFile = ((JarURLConnection)url.openConnection()).getJarFile();
            Enumeration<JarEntry> entries = jarFile.entries();
            while(entries.hasMoreElements()){
                loadJarEntry(entries.nextElement(),clazzPath, clazzSet);
            }
        }catch (Exception e){
            throw new GateException(e);
        }
    }

    private static void loadJarEntry(JarEntry entry , String clazzPath, Set<Class<?>> classSet) throws Exception{
        String entryClassPath = clazzPath;
        String entryName = entry.getName();
        if('/' == entryName.charAt(0)){
            entryName = entryName.substring(1);
        }
        if(entryName.startsWith(clazzPath)){
            int index = entryName.lastIndexOf("/");
            if(index >= 0){
                entryClassPath = entryName.substring(0,index).replaceAll("/",".");
                if(entryName.endsWith(".class") && !entry.isDirectory()){
                    String className = entryName.substring(entryClassPath.length() +1, entryName.length()-6);
                    classSet.add(Class.forName(entryClassPath + '.'+ className));
                }
            }
        }
    }


    private static void addFileUrl(String clazzPath, URL url, Set<Class<?>> clazzSet) throws GateException{
        try {
            String filePath = URLDecoder.decode(url.getFile(),"UTF-8");
            File rootFile = new File(filePath);
            loadClassFile(clazzPath,rootFile, clazzSet);
        } catch (Exception e) {
            throw new GateException(e);
        }
    }

    private static void loadClassFile(String clazzPath, File rootFile ,Set<Class<?>> clazzSet) throws ClassNotFoundException {
        if(!rootFile.exists()){
            return ;
        }
        if(rootFile.isDirectory()){
            for (File childFile: Objects.requireNonNull(rootFile.listFiles())) {
                loadClassFile(clazzPath,childFile, clazzSet);
            }
        }else {
            if(rootFile.isFile() && rootFile.getName().endsWith(".class")){
                String className = rootFile.getName().substring(0, rootFile.getName().lastIndexOf("."));
                ClassLoader classLoader = new ClassLoader() {
                    @Override
                    public Class<?> loadClass(String name) throws ClassNotFoundException {
                        return super.loadClass(name);
                    }
                };
                clazzSet.add(classLoader.loadClass(clazzPath+"."+className));
            }
        }
    }
}
