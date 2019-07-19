package com.hb56.block.model.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Been
 */
public class ApiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApiExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCatIdIsNull() {
            addCriterion("cat_id is null");
            return (Criteria) this;
        }

        public Criteria andCatIdIsNotNull() {
            addCriterion("cat_id is not null");
            return (Criteria) this;
        }

        public Criteria andCatIdEqualTo(Integer value) {
            addCriterion("cat_id =", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotEqualTo(Integer value) {
            addCriterion("cat_id <>", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThan(Integer value) {
            addCriterion("cat_id >", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cat_id >=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThan(Integer value) {
            addCriterion("cat_id <", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThanOrEqualTo(Integer value) {
            addCriterion("cat_id <=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdIn(List<Integer> values) {
            addCriterion("cat_id in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotIn(List<Integer> values) {
            addCriterion("cat_id not in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdBetween(Integer value1, Integer value2) {
            addCriterion("cat_id between", value1, value2, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cat_id not between", value1, value2, "catId");
            return (Criteria) this;
        }

        public Criteria andApiNameIsNull() {
            addCriterion("api_name is null");
            return (Criteria) this;
        }

        public Criteria andApiNameIsNotNull() {
            addCriterion("api_name is not null");
            return (Criteria) this;
        }

        public Criteria andApiNameEqualTo(String value) {
            addCriterion("api_name =", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameNotEqualTo(String value) {
            addCriterion("api_name <>", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameGreaterThan(String value) {
            addCriterion("api_name >", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameGreaterThanOrEqualTo(String value) {
            addCriterion("api_name >=", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameLessThan(String value) {
            addCriterion("api_name <", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameLessThanOrEqualTo(String value) {
            addCriterion("api_name <=", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameLike(String value) {
            addCriterion("api_name like", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameNotLike(String value) {
            addCriterion("api_name not like", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameIn(List<String> values) {
            addCriterion("api_name in", values, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameNotIn(List<String> values) {
            addCriterion("api_name not in", values, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameBetween(String value1, String value2) {
            addCriterion("api_name between", value1, value2, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameNotBetween(String value1, String value2) {
            addCriterion("api_name not between", value1, value2, "apiName");
            return (Criteria) this;
        }

        public Criteria andUrl2IsNull() {
            addCriterion("url2 is null");
            return (Criteria) this;
        }

        public Criteria andUrl2IsNotNull() {
            addCriterion("url2 is not null");
            return (Criteria) this;
        }

        public Criteria andUrl2EqualTo(String value) {
            addCriterion("url2 =", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2NotEqualTo(String value) {
            addCriterion("url2 <>", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2GreaterThan(String value) {
            addCriterion("url2 >", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2GreaterThanOrEqualTo(String value) {
            addCriterion("url2 >=", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2LessThan(String value) {
            addCriterion("url2 <", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2LessThanOrEqualTo(String value) {
            addCriterion("url2 <=", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2Like(String value) {
            addCriterion("url2 like", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2NotLike(String value) {
            addCriterion("url2 not like", value, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2In(List<String> values) {
            addCriterion("url2 in", values, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2NotIn(List<String> values) {
            addCriterion("url2 not in", values, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2Between(String value1, String value2) {
            addCriterion("url2 between", value1, value2, "url2");
            return (Criteria) this;
        }

        public Criteria andUrl2NotBetween(String value1, String value2) {
            addCriterion("url2 not between", value1, value2, "url2");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andApiDescIsNull() {
            addCriterion("api_desc is null");
            return (Criteria) this;
        }

        public Criteria andApiDescIsNotNull() {
            addCriterion("api_desc is not null");
            return (Criteria) this;
        }

        public Criteria andApiDescEqualTo(String value) {
            addCriterion("api_desc =", value, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescNotEqualTo(String value) {
            addCriterion("api_desc <>", value, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescGreaterThan(String value) {
            addCriterion("api_desc >", value, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescGreaterThanOrEqualTo(String value) {
            addCriterion("api_desc >=", value, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescLessThan(String value) {
            addCriterion("api_desc <", value, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescLessThanOrEqualTo(String value) {
            addCriterion("api_desc <=", value, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescLike(String value) {
            addCriterion("api_desc like", value, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescNotLike(String value) {
            addCriterion("api_desc not like", value, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescIn(List<String> values) {
            addCriterion("api_desc in", values, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescNotIn(List<String> values) {
            addCriterion("api_desc not in", values, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescBetween(String value1, String value2) {
            addCriterion("api_desc between", value1, value2, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andApiDescNotBetween(String value1, String value2) {
            addCriterion("api_desc not between", value1, value2, "apiDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescIsNull() {
            addCriterion("req_desc is null");
            return (Criteria) this;
        }

        public Criteria andReqDescIsNotNull() {
            addCriterion("req_desc is not null");
            return (Criteria) this;
        }

        public Criteria andReqDescEqualTo(String value) {
            addCriterion("req_desc =", value, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescNotEqualTo(String value) {
            addCriterion("req_desc <>", value, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescGreaterThan(String value) {
            addCriterion("req_desc >", value, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescGreaterThanOrEqualTo(String value) {
            addCriterion("req_desc >=", value, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescLessThan(String value) {
            addCriterion("req_desc <", value, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescLessThanOrEqualTo(String value) {
            addCriterion("req_desc <=", value, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescLike(String value) {
            addCriterion("req_desc like", value, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescNotLike(String value) {
            addCriterion("req_desc not like", value, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescIn(List<String> values) {
            addCriterion("req_desc in", values, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescNotIn(List<String> values) {
            addCriterion("req_desc not in", values, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescBetween(String value1, String value2) {
            addCriterion("req_desc between", value1, value2, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andReqDescNotBetween(String value1, String value2) {
            addCriterion("req_desc not between", value1, value2, "reqDesc");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleIsNull() {
            addCriterion("header_example is null");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleIsNotNull() {
            addCriterion("header_example is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleEqualTo(String value) {
            addCriterion("header_example =", value, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleNotEqualTo(String value) {
            addCriterion("header_example <>", value, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleGreaterThan(String value) {
            addCriterion("header_example >", value, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleGreaterThanOrEqualTo(String value) {
            addCriterion("header_example >=", value, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleLessThan(String value) {
            addCriterion("header_example <", value, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleLessThanOrEqualTo(String value) {
            addCriterion("header_example <=", value, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleLike(String value) {
            addCriterion("header_example like", value, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleNotLike(String value) {
            addCriterion("header_example not like", value, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleIn(List<String> values) {
            addCriterion("header_example in", values, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleNotIn(List<String> values) {
            addCriterion("header_example not in", values, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleBetween(String value1, String value2) {
            addCriterion("header_example between", value1, value2, "headerExample");
            return (Criteria) this;
        }

        public Criteria andHeaderExampleNotBetween(String value1, String value2) {
            addCriterion("header_example not between", value1, value2, "headerExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleIsNull() {
            addCriterion("req_example is null");
            return (Criteria) this;
        }

        public Criteria andReqExampleIsNotNull() {
            addCriterion("req_example is not null");
            return (Criteria) this;
        }

        public Criteria andReqExampleEqualTo(String value) {
            addCriterion("req_example =", value, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleNotEqualTo(String value) {
            addCriterion("req_example <>", value, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleGreaterThan(String value) {
            addCriterion("req_example >", value, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleGreaterThanOrEqualTo(String value) {
            addCriterion("req_example >=", value, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleLessThan(String value) {
            addCriterion("req_example <", value, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleLessThanOrEqualTo(String value) {
            addCriterion("req_example <=", value, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleLike(String value) {
            addCriterion("req_example like", value, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleNotLike(String value) {
            addCriterion("req_example not like", value, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleIn(List<String> values) {
            addCriterion("req_example in", values, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleNotIn(List<String> values) {
            addCriterion("req_example not in", values, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleBetween(String value1, String value2) {
            addCriterion("req_example between", value1, value2, "reqExample");
            return (Criteria) this;
        }

        public Criteria andReqExampleNotBetween(String value1, String value2) {
            addCriterion("req_example not between", value1, value2, "reqExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleIsNull() {
            addCriterion("resp_example is null");
            return (Criteria) this;
        }

        public Criteria andRespExampleIsNotNull() {
            addCriterion("resp_example is not null");
            return (Criteria) this;
        }

        public Criteria andRespExampleEqualTo(String value) {
            addCriterion("resp_example =", value, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleNotEqualTo(String value) {
            addCriterion("resp_example <>", value, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleGreaterThan(String value) {
            addCriterion("resp_example >", value, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleGreaterThanOrEqualTo(String value) {
            addCriterion("resp_example >=", value, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleLessThan(String value) {
            addCriterion("resp_example <", value, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleLessThanOrEqualTo(String value) {
            addCriterion("resp_example <=", value, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleLike(String value) {
            addCriterion("resp_example like", value, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleNotLike(String value) {
            addCriterion("resp_example not like", value, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleIn(List<String> values) {
            addCriterion("resp_example in", values, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleNotIn(List<String> values) {
            addCriterion("resp_example not in", values, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleBetween(String value1, String value2) {
            addCriterion("resp_example between", value1, value2, "respExample");
            return (Criteria) this;
        }

        public Criteria andRespExampleNotBetween(String value1, String value2) {
            addCriterion("resp_example not between", value1, value2, "respExample");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}