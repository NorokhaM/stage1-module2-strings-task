package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer tokenizer = new StringTokenizer(signatureString, " (),");
        String returnType;
        String methodName;
        String accessModifier=null;
        String firstToken=tokenizer.nextToken();
        if (firstToken.equals("private")||firstToken.equals("public")||firstToken.equals("protected")){
            accessModifier=firstToken;
            returnType=tokenizer.nextToken();
        }
        else {
            returnType=firstToken;
        }
        methodName=tokenizer.nextToken();
        ArrayList<MethodSignature.Argument> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(new MethodSignature.Argument(tokenizer.nextToken(),tokenizer.nextToken()));
        }
        MethodSignature signature = new MethodSignature(methodName,tokens);
        signature.setAccessModifier(accessModifier);
        signature.setReturnType(returnType);
        return signature;
    }

    }


