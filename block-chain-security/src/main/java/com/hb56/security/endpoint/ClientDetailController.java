package com.hb56.security.endpoint;

import com.hb56.common.result.RestInfo;
import com.hb56.common.result.RestUtil;
import com.hb56.security.model.OauthClientDetail;
import com.hb56.security.service.OauthClientDetailService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Been
 * @date 2019/4/26
 */
@RestController
public class ClientDetailController {

    @Autowired
    private OauthClientDetailService detailService;

    @ApiOperation("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/clientDetails")
    public RestInfo getClientDetails(Integer pageNum, Integer pageSize, String resourceIds) {
        return RestUtil.setData(detailService.getClientDetails(pageNum, pageSize, resourceIds));
    }

    @ApiOperation("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/clientDetail/{clientId}")
    public RestInfo getClientDetail(@PathVariable String clientId) {
        return RestUtil.setData(detailService.getClientDetail(clientId.trim()));
    }

    @ApiOperation("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/clientDetail")
    public RestInfo saveClientDetail(@Valid @RequestBody OauthClientDetail clientDetail) {
        if (StringUtils.isBlank(clientDetail.getClientSecret())) {
            return RestUtil.setErrorMsg("Client_secret is null");
        }
        clientDetail.setClientSecret(new BCryptPasswordEncoder().encode(clientDetail.getClientSecret()));
        return RestUtil.setData(detailService.saveClientDetail(clientDetail));
    }


    @ApiOperation("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/clientDetail/{clientId}")
    public RestInfo removeClientDetail(@PathVariable String clientId) {
        return RestUtil.setData(detailService.removeClientDetail(clientId));
    }

    @ApiOperation("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/clientDetail")
    public RestInfo updateClientDetail(@Valid @RequestBody OauthClientDetail clientDetail) {
        if (StringUtils.isNotBlank(clientDetail.getClientSecret())) {
            clientDetail.setClientSecret(new BCryptPasswordEncoder().encode(clientDetail.getClientSecret()));
        }
        int count = detailService.updateClientDetail(clientDetail);
        if (count != 1) {
            return RestUtil.setErrorMsg("No client found with id = " + clientDetail.getClientId());
        }
        return RestUtil.setData(count);
    }

    @ApiOperation("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/clientSecret")
    public RestInfo updateClientSecret(@RequestParam String clientId, @RequestParam String clientSecret) {
        int count = detailService.updateClientSecret(clientId, new BCryptPasswordEncoder().encode(clientSecret));
        if (count != 1) {
            return RestUtil.setErrorMsg("No client found with id = " + clientId);
        }
        return RestUtil.setData(count);
    }

}
