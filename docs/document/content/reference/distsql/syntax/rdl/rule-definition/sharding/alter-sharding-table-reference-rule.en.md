+++
title = "ALTER SHARDING TABLE REFERENCE RULE"
weight = 14
+++

## Description

The `ALTER SHARDING TABLE REFERENCE RULE` syntax is used to alter sharding table reference rule. 

### Syntax

```sql
AlterShardingTableReferenceRule ::=
  'ALTER' 'SHARDING' 'TABLE' 'REFERENCE' 'RULE'  referenceRelationshipDefinition  (',' referenceRelationshipDefinition )*

referenceRelationshipDefinition ::=
  ruleName '(' tableName (',' tableName)* ')'

tableName ::=
  identifier
```

### Supplement

- A sharding table can only be associated with one sharding table reference rule;
- The referenced sharding tables should be sharded in the same storage units and have the same number of sharding nodes. For
  example `ds_${0..1}.t_order_${0..1}` and `ds_${0..1}.t_order_item_${0..1}`;
- The referenced sharding tables should use consistent sharding algorithms. For example `t_order_{order_id % 2}` and `t_order_item_{order_item_id % 2}`;

### Example

#### 1. Alter a sharding table reference rule

```sql
ALTER SHARDING TABLE REFERENCE RULE ref_0 (t_order,t_order_item);
```

#### 2. Alter multiple sharding table reference rules

```sql
ALTER SHARDING TABLE REFERENCE RULE ref_0 (t_order,t_order_item), ref_1 (t_product,t_product_item);
```

### Reserved word

`ALTER`, `SHARDING`, `TABLE`, `REFERENCE`, `RULE`

### Related links

- [Reserved word](/en/reference/distsql/syntax/reserved-word/)
- [CREATE SHARDING TABLE RULE](/en/reference/distsql/syntax/rdl/rule-definition/create-sharding-table-rule/)
