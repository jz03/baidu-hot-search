FROM java:8
RUN mkdir /root/appconf
RUN mkdir /root/appdata
COPY start.sh /root/start.sh
ENV TZ Asia/Shanghai
WORKDIR /root
CMD ["sh","start.sh"]
EXPOSE 8080
