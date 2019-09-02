package sqlinesrt;

import java.beans.FeatureDescriptor;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxtToSql {
    public static void main(String [] args){
     String str=readTxtFile("C:\\Users\\Staryang\\Desktop\\YXproject\\SQL\\txt\\1.txt","C:\\Users\\Staryang\\Desktop\\YXproject\\SQL\\txt\\2.txt");
        Pattern pattern = Pattern.compile("[^\u4E00-\u9FA5]");
        //[\u4E00-\u9FA5]是unicode2的中文区间
        Matcher matcher = pattern.matcher(str);
        //System.out.println(matcher.replaceAll(" "));

        System.out.println(str);
    }
    public static String readTxtFile(String filePath,String filePath2) {
        StringBuilder content = new StringBuilder("");
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            File file1=new File(filePath2);
            File file2 = new File("C:\\Users\\Staryang\\Desktop\\YXproject\\SQL\\txt\\3.txt");
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                InputStreamReader read2 = new InputStreamReader(new FileInputStream(file1));
                BufferedReader bufferedReader = new BufferedReader(read);
                BufferedReader bufferedReader1 = new BufferedReader(read2);
                FileWriter fw = new FileWriter(file2.getAbsoluteFile());
                String lineTxt = "";
                String lineTxt1 = "";
                int i=2847;
                int id=36;
                String s="JLRLY";
                int level=1;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    //System.out.println(lineTxt1);&&(lineTxt1 = bufferedReader1.readLine()) != null
                    String[] split = lineTxt.split("[\\t \\n]+");
                   //System.out.println(split.length);
                   //fw.write(split[2]);
//                     String[] result = getNamePhone(lineTxt);
//                    System.out.println(result);"+split[1]+"
                    String writer1="INSERT INTO DHR_BUSINESS_ENUM_ITEM (ID,CREATED_DATE,CREATED_USER_OID,DELETED,MODIFIED_DATE,MODIFIED_USER_OID,OID,CODE,ITEM_DES,ENUM_ID,ITEM_CODE,ITEM_LEVEL,ITEM_PATH,ITEM_VALUE,PID)VALUES ("+i+",TO_DATE('201908','yyyyMM'),sys_guid(),0,TO_DATE('201908','yyyyMM'),sys_guid(),sys_guid(),'"+s+"','"+split[3]+"',"+id+",'',"+level+",'','"+split[2]+"','');";
                    fw.write(writer1);
                    fw.write("\n");
                    i++;
                    //System.out.println(lineTxt);
                    //content.append(result[1]);
                    content.append("\r\n");// txt换行i
                }
                fw.close();
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return content.toString();
    }

}
