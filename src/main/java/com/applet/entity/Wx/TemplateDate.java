package com.applet.entity.Wx;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateDate {

    private DataValue first;

    private DataValue keyword1;

    private DataValue keyword2;

    private DataValue keyword3;

    private DataValue remark;


    public static class Build {

        private DataValue first;

        private DataValue keyword1;

        private DataValue keyword2;

        private DataValue keyword3;

        private DataValue remark;

        public Build(DataValue first, DataValue remark) {
            this.first = first;
            this.remark = remark;
        }

        public Build keyword1(DataValue value) {
            keyword1 = value;
            return this;
        }

        public Build keyword2(DataValue value) {
            keyword2 = value;
            return this;
        }

        public Build keyword3(DataValue value) {
            keyword3 = value;
            return this;
        }

        public TemplateDate build(){
            return new TemplateDate(this);
        }
    }


    private TemplateDate(Build build) {
        this.first = build.first;
        this.keyword1 = build.keyword1;
        this.keyword2 = build.keyword2;
        this.keyword3 = build.keyword3;
        this.remark = build.remark;
    }
}
