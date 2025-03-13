package com.example.demo.constant;

public enum LineMessageTemplate {
  REPORT_SUCCES(
      "感謝您的通報！🐾 我們已收到您的通報資訊，以下是通報內容：\n- 城市：%s\n- 區域：%s\n- 地址：%s\n- 描述：%s\n\n我們將儘快處理，謝謝您對浪浪的關注與幫助！"),
  REPORT_UPDATE(
      "📢 通報更新：\n您的通報（編號：%s）\n狀態已更新：%s\n\n- 中途之家：%s\n- 地址：%s%s%s\n\n感謝您協助浪浪找到更好的歸屬！"),
  ADOPTION_SUCCES(
      "🎉 感謝您的領養需求！\n您的領養申請已提交，我們將儘快審核並聯繫您。以下是您的需求內容：\n- 領養浪浪：%s\n\n我們期待幫助您找到心儀的毛小孩！🐶🐱"),
  ADOPTION_UPDATE(
      "📢 領養進度更新：\n您的領養申請（編號：%s）\n狀態已更新：%s\n\n- 浪浪名稱：%s\n\n感謝您的耐心等候，我們將與您保持聯繫！"),
  REPORT_REQUEST(
      "📢 新通報需求！\n以下是待處理的通報資訊：\n- 城市：%s\n- 區域：%s\n- 地址：%s\n- 描述：%s\n\n請確認是否可以提供協助，謝謝您的支持！"),
  ADOPTION_REQUEST(
      "📢 新領養申請！\n以下是需要您確認的領養需求：\n- 浪浪名稱：%s\n- 領養人：%s\n\n請確認是否可安排，謝謝您的協助！🐾");


  private final String messageTemplate;

  LineMessageTemplate(String messageTemplate) {
    this.messageTemplate = messageTemplate;
  }

  public static String getReportStatusMessage(String status) {
    switch (status) {
      case "pending":
        return "待處理";
      case "in_progress":
        return "進行中";
      case "resolved":
        return "已完成";
      default:
        return "未知狀態";
    }
  }

  public static String getAdoptionStatusMessage(String status) {
    switch (status) {
      case "pending":
        return "待辦中";
      case "approved":
        return "已通過";
      case "rejected":
        return "未通過";
      default:
        return "未知狀態";
    }
  }

  public String getMessage(Object... args) {
    return String.format(this.messageTemplate, args);
  }
}
