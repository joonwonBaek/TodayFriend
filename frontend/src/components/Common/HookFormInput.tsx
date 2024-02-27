import { useTheme } from '@emotion/react';
import styled from '@emotion/styled';
import { useState } from 'react';
import { FieldValues } from 'react-hook-form';

import { HookFormInputProps } from '@/types/input';

export const HookFormInput = <T extends FieldValues>({
  name,
  register,
  label,
  errors,
  required = false,
  type = 'text',
  formState,
  validation,
  isErrorCheck = false,
  ...props
}: HookFormInputProps<T>) => {
  const theme = useTheme();

  const [inputType, setInputType] = useState(type);

  const inputError = errors && errors[name];
  const inputErrorMessage = inputError && (inputError.message as string);

  const handleToggleType = () => {
    const updatedType = inputType === 'password' ? 'text' : 'password';
    setInputType(updatedType);
  };

  const isValid = () => {
    return formState?.dirtyFields[name] && !formState.errors[name];
  };

  return (
    <InputWrapper>
      {label && <LabelText htmlFor={name}>{label}</LabelText>}
      <Input
        type={inputType}
        {...register(name, {
          ...validation,
          required: required && '값이 입력되지 않았어요',
        })}
        autoComplete="true"
        {...props}
      />
      {type === 'password' && (
        <TogglePasswordButton
          type="button"
          tabIndex={-1}
          onClick={handleToggleType}>
          {/* {inputType === 'password' ? (
            <VisibilityOffIcon />
          ) : (
            <VisibilityIcon />
          )} */}
        </TogglePasswordButton>
      )}
      {formState?.isSubmitted && inputErrorMessage && (
        <InputErrorWrapper>
          <InputErrorMessage>{inputErrorMessage}</InputErrorMessage>
        </InputErrorWrapper>
      )}
      {isErrorCheck && (
        <InputErrorCheckButton
          type="button"
          tabIndex={-1}
          color={isValid() ? theme.colors.secondary : theme.colors.gray}>
          {/* <CheckIcon /> */}
        </InputErrorCheckButton>
      )}
    </InputWrapper>
  );
};
const InputWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  position: relative;
`;

const Input = styled.input`
  width: 100%;
  border: 1px solid #dadada;
  border-radius: 1rem;
  padding: 0.2rem 0.4rem;
  outline: none;
`;

const InputErrorWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 1rem;
  position: relative;
`;

const InputErrorMessage = styled.span`
  font-size: 1.2rem;
  color: red;
  position: absolute;
  transform: translateY(50%);
`;

export const TogglePasswordButton = styled.button`
  position: absolute;
  top: 0;
  right: 1rem;
  color: #aaaaaa;
`;

const LabelText = styled.label`
  font-size: 1.2rem;
  color: ${(props) => props.theme.colors.gray};
  padding-bottom: 0.8rem;
`;

const InputErrorCheckButton = styled.button`
  position: absolute;
  top: 50%;
  right: 0.5rem;
  transform: translateY(-0.5rem);
  color: ${({ color }) => color};
`;
