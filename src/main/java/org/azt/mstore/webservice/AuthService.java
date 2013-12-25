package org.azt.mstore.webservice;

import org.azt.mstore.dao.AuthorityDao;
import org.azt.mstore.model.Authority;
import org.azt.mstore.utility.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * Created by azhang6 on 13-12-24.
 */
@Controller
@RequestMapping("service/auths")
public class AuthService {

    @Autowired
    private AuthorityDao authorityDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Authority> getAuths() {
        return this.authorityDao.getAllAuthorities();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public int addAuth(HttpServletRequest request) throws Exception {
        try {
            Authority authority = JSONUtil.readToObject(request.getInputStream(), Authority.class);

            Authority newAuth = this.authorityDao.getAuthorityByName(authority.getAuthorityName());

            if (newAuth != null) {
                throw new Exception("Authority already exist.");
            } else {
                authorityDao.saveAuthority(authority);

                newAuth = this.authorityDao.getAuthorityByName(authority.getAuthorityName());

                return newAuth != null ? newAuth.getId() : -1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteAuth(@PathVariable("id") int id) {
        int result = this.authorityDao.deleteAuthority(id);

        return result > 0;
    }
}
