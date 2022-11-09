FROM gitpod/workspace-full
USER gitpod
RUN bash -c ". ~/.sdkman/bin/sdkman-init.sh && \
    sdk install java 19-zulu && \
    sdk default java 19-zulu"