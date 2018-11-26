import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class MainHE {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TreeAndKTuple solver = new TreeAndKTuple();
        solver.solve(1, in, out);
        out.close();
    }

    static class TreeAndKTuple {
        public int mod = 1000000007;
        public int n;
        public int k;
        public List<Integer>[] graph;
        public List<Integer>[] or;
        public int[] val;
        public int[] start;
        public int[] end;
        public int idx;

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            Thread t =
                    new Thread(null, () -> {
                        new TreeAndKTuple()._solve(1, in, out);
                        out.close();
                    }, "1", Runtime.getRuntime().maxMemory());
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void _solve(int testNumber, InputReader in, OutputWriter out) {
            n = in.nextInt();
            k = in.nextInt();
            val = in.readIntArray(n);
            or = Stream.generate(ArrayList::new).limit(100010).toArray(List[]::new);
            graph = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
            for (int i = 0; i < n - 1; i++) {
                int a = in.nextInt() - 1, b = in.nextInt() - 1;
                graph[a].add(b);
                graph[b].add(a);
            }

            start = new int[n];
            end = new int[n];
            idx = 0;
            dfs(0, -1);

            int[] cval = new int[n];
            for (int i = 0; i < n; i++) {
                cval[i] = 1;
            }
            for (int e = 0; e < k; e++) {
                int[] nval = new int[n];
                BITMod bb = new BITMod(n, mod);
                for (int i = 0; i < or.length; i++) {
                    for (int j : or[i]) {
                        nval[j] = bb.query(end[j] - 1) - bb.query(start[j]);
                        if (nval[j] < 0) nval[j] += mod;
                        bb.update(start[j], cval[j]);
                    }
                }
                cval = nval;
            }
            int res = 0;
            for (int x : cval) {
                res += x;
                if (res >= mod) res -= mod;
            }
            out.println(res);
        }

        public void dfs(int node, int par) {
            start[node] = idx++;
            or[val[node]].add(node);
            for (int next : graph[node]) {
                if (next == par) continue;
                dfs(next, node);
            }
            end[node] = idx;
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int[] readIntArray(int tokens) {
            int[] ret = new int[tokens];
            for (int i = 0; i < tokens; i++) {
                ret[i] = nextInt();
            }
            return ret;
        }

        public int read() {
            if (this.numChars == -1) {
                throw new InputMismatchException();
            } else {
                if (this.curChar >= this.numChars) {
                    this.curChar = 0;

                    try {
                        this.numChars = this.stream.read(this.buf);
                    } catch (IOException var2) {
                        throw new InputMismatchException();
                    }

                    if (this.numChars <= 0) {
                        return -1;
                    }
                }

                return this.buf[this.curChar++];
            }
        }

        public int nextInt() {
            int c;
            for (c = this.read(); isSpaceChar(c); c = this.read()) {
                ;
            }

            byte sgn = 1;
            if (c == 45) {
                sgn = -1;
                c = this.read();
            }

            int res = 0;

            while (c >= 48 && c <= 57) {
                res *= 10;
                res += c - 48;
                c = this.read();
                if (isSpaceChar(c)) {
                    return res * sgn;
                }
            }

            throw new InputMismatchException();
        }

        public static boolean isSpaceChar(int c) {
            return c == 32 || c == 10 || c == 13 || c == 9 || c == -1;
        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void close() {
            writer.close();
        }

        public void println(int i) {
            writer.println(i);
        }

    }

    static class BITMod {
        public int mod;
        private int[] tree;
        private int N;

        public BITMod(int N, int mod) {
            this.N = N;
            this.mod = mod;
            this.tree = new int[N + 3];
        }

        public int query(int K) {
            K += 2;
            if (K <= 0) return 0;
            int sum = 0;
            for (int i = K; i > 0; i -= (i & -i)) {
                sum += tree[i];
                if (sum >= mod) sum -= mod;
            }
            return sum;
        }

        public void update(int K, int val) {
            K += 2;
            for (int i = K; i < tree.length; i += (i & -i)) {
                tree[i] += val;
                if (tree[i] >= mod) tree[i] -= mod;
            }
        }

    }
}