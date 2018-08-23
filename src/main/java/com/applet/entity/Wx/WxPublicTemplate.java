package com.applet.entity.Wx;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxPublicTemplate {

      private String touser;

      private String template_id;

      private String url;

      private MiniData miniprogram;

      private TemplateDate data;

      public WxPublicTemplate(String touser, String template_id, TemplateDate data) {
            this.touser = touser;
            this.template_id = template_id;
            this.data = data;
      }
}
