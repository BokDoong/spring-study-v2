package com.spring.mystudy.common.validator;

import com.spring.mystudy.exception.code.ErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryExistsValidator implements ConstraintValidator<VerifyNotExistedCategory, List<Long>> {
    @Override
    public void initialize(VerifyNotExistedCategory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    // 실제로 여기서 검증 -> 들어오는 값은 List<Long> 으로 선언.
    @Override
    public boolean isValid(List<Long> requestCategoryIds, ConstraintValidatorContext context) {

        boolean isValid = checkCategoryExists(requestCategoryIds);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.CATEGORY_NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;
    }

    private boolean checkCategoryExists(List<Long> requestCategoryIds) {
        for (int i = 0; i < requestCategoryIds.size(); i++) {
            if (!CategoriesExistValidator.validate(requestCategoryIds.get(i))) {
                return false;
            }
        }
        return true;
    }
}
