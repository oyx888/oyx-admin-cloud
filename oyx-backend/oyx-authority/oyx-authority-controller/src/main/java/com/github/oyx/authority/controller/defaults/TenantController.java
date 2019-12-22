package com.github.oyx.authority.controller.defaults;


import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.oyx.authority.dto.defaults.TenantPageDTO;
import com.github.oyx.authority.dto.defaults.TenantSaveInitDto;
import com.github.oyx.base.R;
import com.github.oyx.dozer.DozerUtils;
import com.github.oyx.log.annotation.SysLog;
import com.github.oyx.database.mybatis.conditions.query.LbqWrapper;
import com.github.oyx.database.mybatis.conditions.Wraps;
import com.github.oyx.authority.entity.defaults.Tenant;
import com.github.oyx.authority.dto.defaults.TenantSaveDTO;
import com.github.oyx.authority.dto.defaults.TenantUpdateDTO;
import com.github.oyx.authority.service.defaults.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.github.oyx.base.entity.SuperEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.oyx.base.BaseController;

import static com.github.oyx.authority.enumeration.defaults.TenantStatusEnum.NORMAL;

/**
 * <p>
 * 前端控制器
 * 企业
 * </p>
 *
 * @author oyx
 * @date 2019-12-19
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/tenant")
@Api(value = "Tenant", tags = "企业")
public class TenantController extends BaseController {

    @Autowired
    private TenantService tenantService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询企业
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询企业", notes = "分页查询企业")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询企业")
    public R<IPage<Tenant>> page(TenantPageDTO data) {
        IPage<Tenant> page = getPage();
        // 构建值不为null的查询条件
        Tenant pageEntity = dozer.map(data, Tenant.class);
        LbqWrapper<Tenant> query =Wraps.lbQ(pageEntity)
                .leFooter(Tenant::getCreateTime, data.getEndCreateTime())
                .geHeader(Tenant::getCreateTime, data.getStartCreateTime())
                .orderByDesc(Tenant::getCreateTime);
        tenantService.page(page, query);
        return success(page);
    }

    @ApiOperation(value = "查询所有企业", notes = "查询所有企业")
    @GetMapping
    public R<List<Tenant>> list(){
        return success(tenantService.list(Wraps.<Tenant>lbQ().eq(Tenant::getStatus, NORMAL)));
    }

    /**
     * 查询企业
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询企业", notes = "查询企业")
    @GetMapping("/{id}")
    @SysLog("查询企业")
    public R<Tenant> get(@PathVariable Long id) {
        return success(tenantService.getById(id));
    }


    @ApiOperation(value = "检测租户是否存在", notes = "检测租户是否存在")
    @GetMapping("/check/{code}")
    public R<Boolean> check(@PathVariable("code") String code) {
        return success(tenantService.check(code));
    }

    /**
     * 修改企业
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改企业", notes = "修改企业不为空的字段")
    @PutMapping
    @SysLog("修改企业")
    public R<Tenant> update(@RequestBody @Validated(SuperEntity.Update.class) TenantUpdateDTO data) {
        Tenant tenant = dozer.map(data, Tenant.class);
        tenantService.updateById(tenant);
        return success(tenant);
    }

    /**
     * 删除企业
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除企业", notes = "根据id物理删除企业")
    @DeleteMapping
    @SysLog("删除企业")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        tenantService.removeByIds(ids);
        return success(true);
    }

    @ApiOperation(value = "新增企业", notes = "新增企业不为空的字段")
    @PostMapping
    public R<Tenant> save(@RequestBody @Validated TenantSaveDTO data) {
        Tenant tenant = tenantService.save(data);
        return success(tenant);
    }
}
