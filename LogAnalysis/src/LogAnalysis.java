import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class LogAnalysis {
    public static void main(String[] args) {
        String file = args[0];
        
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            //listにURLの抽出
            String regex = "(\"(GET|PUT|POST|PATCH|DELETE|HEAD) ([\\w\\- ./?%&=]+)\") "; //ログ全体のURLの検出
            String regex2 = "(\"(POST) ([\\w\\- ./?%&=]+)\") "; //サイト内検索URL検出
            Pattern p = Pattern.compile(regex);
            Pattern p2 = Pattern.compile(regex2);
            
           
            while ((line = br.readLine()) != null) {
            	
            	Matcher m = p.matcher(line);
            	Matcher m2 = p2.matcher(line);
            
	            while (m.find()) {
	            	list.add(m.group());
	            }
	            while (m2.find()) {
	            	list2.add(m2.group());
	            }
            }
            //key:URL ,value:重複回数として整理
            Map<String, Integer> map = list.stream().collect(
            		Collectors.toMap(s -> s, s -> Collections.frequency(list, s), (s1, s2) -> s1));
            
           //降順へ変換
            List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(map.entrySet());
            
            Collections.sort(list_entries, new Comparator<Entry<String, Integer>>() {
                
                public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2)
                {
                    return obj2.getValue().compareTo(obj1.getValue());
                }
            });
            int i = 0;
            for(Entry<String, Integer> entry : list_entries) {
            	i++;
            	
                System.out.println("アクセス回数" + i + "位" + entry.getKey() + " : " + entry.getValue() + "");
                if (i == 20) {
                	break;
                }
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

    


