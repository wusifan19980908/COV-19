package cdu_2017.xuye.cov19.register.controller;

import cdu_2017.xuye.cov19.common.model.R;
import cdu_2017.xuye.cov19.register.model.WayInfo;
import cdu_2017.xuye.cov19.register.service.WayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;


/**
 * 
 * @date 2021-02-09 14:32:41
 */
@RestController
@RequestMapping("cov-19/wayinfo")
public class WayInfoController {
    @Autowired
    private WayInfoService wayInfoService;
    @RequestMapping("save")
    public R save(@RequestBody WayInfo wayInfo){
        wayInfoService.save(wayInfo);
        return R.ok();
    }
    @RequestMapping("update")
    public R update(@RequestBody WayInfo wayInfo){
        wayInfoService.updateById(wayInfo);
        return R.ok();
    }
    @RequestMapping("info/{id}")
    public R info(@PathVariable Integer id){
        return R.put(wayInfoService.getById(id));
    }
    @RequestMapping("delete/{id}")
    public R delete(@PathVariable Integer id){
        wayInfoService.removeById(id);
        return R.ok();
    }
}
