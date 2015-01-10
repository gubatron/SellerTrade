/**
 * The MIT License
 ===============

 Copyright (C) 2015 SellerTrade Developers

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject
 to the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.seller.trade.core;

public class ConfigurationKeys {
    public static final String ST_VERSION = "st.version";
    public static final String ST_API_PORT = "st.api_port";
    public static final String ST_SITE_NAME = "st.site_name";
    public static final String ST_SITE_HTTP_PREFIX = "st.site_http_prefix";
    public static final String ST_SITE_SLOGAN = "st.site_slogan";
    public static final String ST_SITE_DESCRIPTION = "st.site_description";
    public static final String ST_SITE_KEYWORDS = "st.site_keywords";
    public static final String ST_SEARCH_HASHTAGS = "st.search_hashtags";
    public static final String ST_SITE_USER_ID = "st.site_user_id";
    public static final String ST_JSON_PRETTY_PRINT = "st.json_pretty_print";
    public static final String ST_API_SERVERS_IPS = "st.api_servers_ips";
    public static final String ST_LIB_PATH = "st.lib_path";
    public static final String ST_IS_LOBBY_SERVER = "st.is_lobby_server";

    public static final String ST_EMAIL_USERNAME = "st.emails.username";
    public static final String ST_EMAIL_PASSWORD = "st.emails.password";
    public static final String ST_EMAIL_SMTP_HOST = "st.emails.smtp.host";
    public static final String ST_EMAIL_SMTP_PORT = "st.emails.smtp.port";
    public static final String ST_EMAIL_BCC = "st.emails.bcc";
    public static final String ST_EMAIL_FROM = "st.emails.from";
    public static final String ST_EMAIL_RESET_RETRY_TIMEOUT = "st.emails.reset_retry_timeout";
    public static final String ST_EMAIL_TEST = "st.emails.test"; 
    public static final String ST_MAILING_LIST_SENDER_SLEEPING_TIME = "st.emails.mailing_list_sender_sleeping_time";
    public static final String ST_SEND_ACCESS_CONTROL_ALLOW_ORIGIN = "st.send_access_control_allow_origin";

    public static final String AWS_ACCESS="aws.access";
    public static final String AWS_SECRET="aws.secret";
    public static final String AWS_SQS_SERVICE_URL = "aws.sqs.service_url";
    public static final String AWS_SQS_WEEKLY_DIGEST_URL = "aws.sqs.weekly_digest_url";
    public static final String AWS_SQS_NEWSLETTER_URL = "aws.sqs.newsletter_url";
    
    public static final String WEB_PATH = "website.path";


    protected ConfigurationKeys() {
    }
}