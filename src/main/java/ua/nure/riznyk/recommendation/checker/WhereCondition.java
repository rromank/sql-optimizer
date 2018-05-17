package ua.nure.riznyk.recommendation.checker;

import gudusoft.gsqlparser.EExpressionType;
import gudusoft.gsqlparser.nodes.IExpressionVisitor;
import gudusoft.gsqlparser.nodes.TExpression;
import gudusoft.gsqlparser.nodes.TParseTreeNode;

import java.util.ArrayList;
import java.util.List;

public class WhereCondition implements IExpressionVisitor {

    private TExpression condition;
    private List<String> columns;

    public WhereCondition(TExpression expr) {
        this.condition = expr;
    }

    public synchronized List<String> getColumns() {
        columns = new ArrayList<>();
        this.condition.inOrderTraverse(this);
        return columns;
    }

    private boolean isCompareCondition(EExpressionType t) {
        return ((t == EExpressionType.simple_comparison_t)
                || (t == EExpressionType.group_comparison_t) || (t == EExpressionType.in_t));
    }

    public boolean exprVisit(TParseTreeNode pnode, boolean pIsLeafNode) {
        TExpression lcexpr = (TExpression) pnode;
        if (isCompareCondition(lcexpr.getExpressionType())) {
            columns.add(lcexpr.getLeftOperand().toString());
        }
        return true;
    }
}
