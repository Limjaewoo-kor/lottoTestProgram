import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean start = true;

        System.out.println("로또 번호를 생성기를 시작합니다.\n");

       while (start){

           System.out.println("다시 진행하려면 'y' 종료하려면 'n'을 입력하세요 ");

           Map<String,Integer> map = new HashMap<>();
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           StringTokenizer st = new StringTokenizer(br.readLine());
           int lankChk=0;

           String check = st.nextToken();

           if(check.equals("N") || check.equals("n") ){
               start = false;
               System.out.println("로또 번호 생성기를 종료합니다.");

           }else if(check.equals("Y") || check.equals("y")){
               System.out.println(" 로또번호 생성 횟수와 몇번 반복된 숫자를 출력 할 것인지 공백으로 구분하여 입력하세요. " +
                       "\n ex) '로또번호 6개의 수를 10000번 생성하고 6개의 수가 2번 이상 모두 겹친 수를 확인하겠다'라고하면 \n--> 10000 2");

               st = new StringTokenizer(br.readLine());
               try{
                   if(st.countTokens() == 2){
                       int cnt = Integer.parseInt(st.nextToken());
                       int lank = Integer.parseInt(st.nextToken());


                       for (int k = 0; k < cnt ; k++) {
                           int lotto[] = new int[6];

                           for (int i = 0; i < 6; i++)
                           {
                               lotto[i] = (int)(Math.random() * 45) + 1;

                               for (int j = 0; j < i; j++) {
                                   if (lotto[j] == lotto[i]) {
                                       i--;
                                   }
                               }
                           }

                           Arrays.sort(lotto);
                           String lottoNumber = Arrays.toString(lotto);
                           map.put(lottoNumber,map.getOrDefault(lottoNumber,0)+1);
                       }

                       List<String> keySet = new ArrayList<>(map.keySet());
                       keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));

                       for (String key : keySet) {
                           if(map.get(key) >= lank){
                               System.out.print("로또 번호 : " + key);
                               System.out.println(", 중복 횟수 : " + map.get(key));
                               lankChk++;
                           }
                       }
                       if(lankChk == 0){
                           System.out.println("이번 번호 생성에서 6개의 수가 모두 겹친 경우가 없었습니다.");
                           System.out.println("생성횟수 및 반복 수를 조정해보는건 어떨까요? \n");
                       }
                   }else {
                       System.out.println("잘못누르셨습니다.\n");
                   }

               }catch (NumberFormatException e){
                   System.out.println("숫자를 입력하세요.\n");
               }catch (Exception e){
                   System.out.println("잘못누르셨습니다.\n");
               }
           }else {
               System.out.println("잘못누르셨습니다.\n");
           }
       }

    }
}