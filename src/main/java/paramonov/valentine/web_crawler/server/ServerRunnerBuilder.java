package paramonov.valentine.web_crawler.server;

class ServerRunnerBuilder {
    private int port;
    private WebCrawlerInitializer initializer;

    private ServerRunnerBuilder() {
    }

    public static ServerRunnerBuilder buildRunnerWith() {
        return new ServerRunnerBuilder();
    }

    public ServerRunnerBuilder port(int port) {
        this.port = port;
        return this;
    }

    public ServerRunnerBuilder initializer(WebCrawlerInitializer initializer) {
        this.initializer = initializer;
        return this;
    }

    public ServerRunner build() {
        final ServerRunner runner = new ServerRunner(port);
        if (initializer == null) {
            return runner;
        }
        runner.setHandler(initializer.buildHandler());
        return runner;
    }
}
