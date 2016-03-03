package me.afsd.dao;

import me.afsd.dao.base.query.MatchType;
import me.afsd.dao.base.query.Query;
import me.afsd.dao.base.query.QueryWord;
import me.afsd.domain.Company;

/**
 * User: afsd
 * Date: 2016/3/1
 * Time: 11:30
 */
public class CompanyQuery extends Query<Company> {
    @QueryWord(matchType = MatchType.like)
    private String name;

    @QueryWord
    private String address;

    @QueryWord("createdBy.createdBy.id")
    private Long userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
