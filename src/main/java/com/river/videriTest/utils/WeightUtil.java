package com.river.videriTest.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author jli 
 * E0最小=0，E0最大=E0最小+权值0；
 * En最小=En-1最大，En最大=En最小+权值n。 
 * 即当前元素概率范围，最小值是上一个元素的最大值，
 * 最大值是当前元素的最小值，加上当前元素的权值
 */
public class WeightUtil {

    /**
     * 需要做一个抽奖系统，需求：抽中特等奖、一等奖、二等奖、三等奖的概率分别为1:2:3:4
     * @param weight
     * @return
     */
   public static int random(List<Integer> weight) {
       List<Integer> weigthTmp = new ArrayList<>();
       weigthTmp.add(0);
       Integer sum = 0;
       
      for( Integer d : weight) {
          sum += d;
          weigthTmp.add(sum);
      }
      
      Random random = new Random();
      
      int rand = random.nextInt(sum);
      
      int index  = 0;
      //权重随机数: 从最大范围开始找，weight会大于rand，当第一个rand大于或等于weight是结束
      for(int i = weigthTmp.size()-1; i>0; i--) {
          if(rand >= weigthTmp.get(i)) {
              index  = i;
              break;
          }         
      }
      return index;      
   }
   
   public static void main(String[] args) {
       List<Integer> weight = new ArrayList<>();
       weight.add(1);
       weight.add(2);
       weight.add(3);
       weight.add(4);
       for(int i = 0;i<1000;i++) {
           System.out.println(random(weight));
       }     
}
}
