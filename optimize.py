import re

# Constant Folding
def constant_folding(expression):
    try:
        variable, expr = expression.split('=')
        result = eval(expr.strip())  # Use carefully, assume input is safe
        return f"{variable.strip()} = {result}"
    except:
        return expression

# Token splitter
def separate_vars_operators(statement):
    pattern = r"([a-zA-Z_][a-zA-Z0-9_]*|\d+|\S)"
    return re.findall(pattern, statement)

# Copy Propagation
def copy_propagation(input_lines):
    variables = {}
    output_lines = []
    replace = {}

    for line in input_lines:
        line = line.strip()
        if '=' not in line:
            continue
        variable, expression = map(str.strip, line.split('='))

        # Replace variables if needed
        separated = separate_vars_operators(expression)
        for i in range(len(separated)):
            if separated[i] in replace:
                separated[i] = replace[separated[i]]
        new_expression = ''.join(separated)

        # If expression is a copy, store mapping
        if len(separated) == 1:
            replace[variable] = separated[0]

        output_lines.append(f"{variable} = {new_expression}")
        variables[variable] = new_expression
    return output_lines

# Common Subexpression Elimination
def common_subexpression_elimination(input_lines):
    expressions = {}
    output_lines = []

    for line in input_lines:
        variable, expression = map(str.strip, line.split('='))
        for var, exp in expressions.items():
            if expression == exp:
                expression = var
                break
        output_lines.append(f"{variable} = {expression}")
        expressions[variable] = expression
    return output_lines

# Main optimizer
def optimize():
    input_lines = [
        "T1 = 5*3+10",
        "T3 = T1",
        "T2 = T1+T3",
        "T5 = 4*T2",
        "T6 = 4*T2+100"
    ]

    print("Original Code:")
    for line in input_lines:
        print(line)

    # Step 1: Constant Folding
    folded = [constant_folding(line) for line in input_lines]

    # Step 2: Copy Propagation
    propagated = copy_propagation(folded)

    # Step 3: Common Subexpression Elimination
    optimized = common_subexpression_elimination(propagated)

    print("\nOptimized Code:")
    for line in optimized:
        print(line)

optimize()
