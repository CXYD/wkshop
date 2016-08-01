/**
 * 校验号码格式
 */
function checkNumberFormat(numId) {
    return /^((13|15|18|14|17)+\d{9})$/.test(numId);
}