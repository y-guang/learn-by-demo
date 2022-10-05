# Unicode

## 字符编码模型

1. 抽象字符表 ACR (Abstract Character Repertoire). 字符的抽象, 而非形态的抽象. 例如不同书体的山是同一个字符. 而同一个形态的字符可能有不同的抽象含义, 如 "Latin Capital Letter K" 和 "KELVIN SIGN"
2. 编码字符集 CCS (Coded Character Set). 上一层 (ACR) 中的字符到码点 (code point) 的映射 (双射的).
3. 字符编码形式 CEF (Character Encoding Form). 码点到计算机内的 representation 的映射.
   1. 定义码元 (code unit), 即内部编码组成的单位. 码元可能是非 $2^n$-bit 大小的.
   2. 定义表达规则, 用一个或多个码元来表示码点.
4. 字符编码方案 CES (Character Encoding Scheme). 大小端.

### Note

抽象字符未必可见.

第二 (字符集) 和三层 (字符编码)之间是多对多的.
- 字符集可以被多种编码方式编码. 
- 同一种编码方式可能对应多种字符集.

## Unicode

### 设计原则

- 抽象字符原则: 面向抽象字符而不是字形或字意编码.
- 动态组合原则: 使用简单的字符组合复杂字符，而不是为复杂字符单独编码
- round-trip rule: 任何现有字符集中的任何一个字符，可以转换成 Unicode 字符集中的字符，并且从 Unicode 中的这个字符再转换回去后还是原来那个字符. 愿称之为.

和前两条产生冲突时, 回译原则作为前两条规则的例外.

#### Note

预合成字符 (precomposed character), 为了兼顾现有字符集方案的合成字. 例如 ㈠ 之于（一）. 按照 Unicode 的观点, 软件在实现时, 应让他们等价. 然而大量现有实现并没有实装这一特性.

### 三种实现

Unicode 标准有 UTF-16, UTF-8, 和 UTF-32 三种实现.

### UTF-8

code unit 为 8 bits.

- 首位为`0`, 单码元字, 也即 ASCII 字符.
- 首位为`1`, 多码元字. 首码元最高两位为 `11`, 后续码元最高两位为 `10`.
- 首码元高位有几个`1`, 则有几个字节以表示该字符.

### UTF-16

code unit 为 16 bits.
- 高 6 位为标志位. 
  - 若为双码元字, 则首码元为高位代理 (high surrogates, `110110`), 次码元为低位代理 (low surrogates, `110111`).
  - 单码元字, 则首 6 位不可能为提到的这两者.
  - 因为这一编码规则, 单码字 `D800-DFFF` 是保留的.

#### 转换规则

$$
C_H = (U - 10000_{16}) / 400_{16} + D800_{16} \\
C_L = (U - 10000_{16}) \bmod 400_{16} + DC00_{16}
$$

其中, $C_H$ 为高位码元, $C_L$ 为低位码元.

其含义为, 

1. Unicode 的码点 (code point) 减去 $10000_{16}$, 因为从 `0000` 到 `FFFF`, 已经被单码元解决了. 
2. 之后除以或取模 $400_{16}$, 这样就得到了两个码元的低 10 位.

将码点转换回 Unicode
$$
U = (C_H - D800_{16}) \times 400_{16} + (C_L - DC00_{16}) + 10000_{16}
$$

### UTF-32

直接用32位表示.


## ref

编码胡同的推文

- [字符集编码（补）：字符编码模型](https://blog.csdn.net/zhang0402030211/article/details/123099363)
- [字符集编码（中）：Unicode](https://mp.weixin.qq.com/s/pQdcuTs5pWxQ1-CEE0n36A)
- [字符集编码（下）：UTF](https://www.cfanz.cn/mobile/resource/detail/wAZLMnqMDYkjy)