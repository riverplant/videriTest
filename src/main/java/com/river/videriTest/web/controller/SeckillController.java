package com.river.videriTest.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.videriTest.domain.Seckill;
import com.river.videriTest.dto.Exposer;
import com.river.videriTest.dto.SeckillExecution;
import com.river.videriTest.dto.SeckillResult;
import com.river.videriTest.enums.SeckillStatEnum;
import com.river.videriTest.service.dao.SeckillService;
import com.river.videriTest.service.exception.RepeatKillException;
import com.river.videriTest.service.exception.SeckillCloseException;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
    @Autowired private SeckillService seckillService;
    
    private String message = "Hello World";
    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
       List<Seckill> list = seckillService.queryAll(-1, -1);
       model.addAttribute("list", list); 
        return "list";
    }
    
    @GetMapping(value = "/{seckillId}/detail")
    public String detail(@PathVariable("seckillId") Long seckillId, Model modle) {
        
        if(seckillId == null) {
            return "redirect: /seckill/list";
        }
        Seckill seckill = seckillService.queryById(seckillId);
        if(seckill == null) {
            return "forward:/seckill/list";
        }
        modle.addAttribute("seckill", seckill);
        return "detail";
    }
    /**
     * 秒杀活动: 放在服务器端缓存(Redis):  请求地址 -> Redis  -> Mysql
     * @param seckillId
     * @return
     */
    @PostMapping(value = "/{seckillId}/exposer",produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(Long seckillId) {
        
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<>(true, exposer); 
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            result = new SeckillResult<>(false, e.getMessage()); 
        }
        return result;
    }
    
    /**
     * 秒杀活动: 放在服务器端缓存(Redis)
     * @param seckillId
     * @param md5
     * @param phone
     * @return
     */
    @PostMapping(value = "/{seckillId}/{md5}/execution",produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(
            @PathVariable("seckillId") Long seckillId, 
            @PathVariable("md5") String md5, 
            @CookieValue(value = "killphone", required = false) Long phone){
        
        if(phone == null) {
            return new SeckillResult<>(false, "未注册");
        }
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<>(true, seckillExecution); 
        }catch (SeckillCloseException e1) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<>(false, seckillExecution); 
        } catch (RepeatKillException e2) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<>(false, seckillExecution); 
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<>(false, seckillExecution); 
        }
    }
    
    @GetMapping(value = "/time/now",produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
    
}
