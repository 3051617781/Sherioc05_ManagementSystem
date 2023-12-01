package dao;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

public class DataDao {
    public static final String[] columnNames= {"用户名","密码","角色"};
    public static final List<String[]>data = new ArrayList<>();
    private static int maxId = 10;
    static {
        for(int i = 0; i < maxId; i++){
            data.add(new String[]{i+"1", "a"+i,"b"+i,});
        }
    }

    //将list集合转化为维数组
    public static String[][] toList(List<String[]> list){
        String[][] result = new String[list.size()][];
        for(int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }

    public static String[][] search(int col, String text){
        List<String[]>result = new ArrayList<>();
        for(String[] d: data){
            if(d[col].toString().contains(text)){
                result.add(d);
            }
        }
        return toList(result);
    }

}
