package com.dalsom.management.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
@AllArgsConstructor
public class PageParameter {

    private int pageNumber;
    private int pageSize;

    public Pageable toPageable() {
        return PageRequest.of(pageNumber - 1, pageSize);
    }

    public static class PageParameterArgumentResolver implements HandlerMethodArgumentResolver {

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return parameter.getParameterType().isAssignableFrom(PageParameter.class);
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
            HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
            String numberParam = request.getParameter("pageNumber");
            String sizeParam = request.getParameter("pageSize");

            int pageNumber = parseIntOrDefault(numberParam, 1);
            int pageSize = parseIntOrDefault(sizeParam, 10);

            return new PageParameter(pageNumber, pageSize);
        }

        private int parseIntOrDefault(String param, int defaultValue) {
            if (ObjectUtils.isEmpty(param)) {
                return defaultValue;
            }

            try {
                return Integer.parseInt(param.trim());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
    }
}
