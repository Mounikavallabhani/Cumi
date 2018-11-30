package com.arkainfotechpvt.cumi.model;

/**
 * Created by Grepthor_9 on 2/19/2018.
 */
public  class Products {
        private String _productid;
        private String _productname;
        private String _productnamea;
        private String _productnameb;
        private String _productnamec;
        private String _productnamed;

        public Products(String _productid, String _productname, String _productnamea, String _productnameb, String _productnamec, String _productnamed) {
                this._productid = _productid;
                this._productname = _productname;
                this._productnamea = _productnamea;
                this._productnameb = _productnameb;
                this._productnamec = _productnamec;
                this._productnamed = _productnamed;
        }

        public String get_productid() {
                return _productid;
        }

        public void set_productid(String _productid) {
                this._productid = _productid;
        }

        public String get_productname() {
                return _productname;
        }

        public void set_productname(String _productname) {
                this._productname = _productname;
        }

        public String get_productnamea() {
                return _productnamea;
        }

        public void set_productnamea(String _productnamea) {
                this._productnamea = _productnamea;
        }

        public String get_productnameb() {
                return _productnameb;
        }

        public void set_productnameb(String _productnameb) {
                this._productnameb = _productnameb;
        }

        public String get_productnamec() {
                return _productnamec;
        }

        public void set_productnamec(String _productnamec) {
                this._productnamec = _productnamec;
        }

        public String get_productnamed() {
                return _productnamed;
        }

        public void set_productnamed(String _productnamed) {
                this._productnamed = _productnamed;
        }
}
