package dev.remitano.infrastructure.request;

public class DefaultRequest {

  private Integer value;
  private String label;

  public DefaultRequest() {
  }

  public DefaultRequest(Integer value, String label) {
    this.value = value;
    this.label = label;
  }

  public DefaultRequest(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
