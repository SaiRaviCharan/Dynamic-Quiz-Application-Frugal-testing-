const QUESTION_TIME_LIMIT = 45;
let answerChartInstance = null;
let timeChartInstance = null;

const statusPill = document.getElementById('statusPill');
const feedbackBanner = document.getElementById('feedbackBanner');
const timerDisplay = document.getElementById('timerDisplay');
const timerRing = document.getElementById('timerRing');
const timerSection = document.getElementById('timerContainer');
const performanceSummary = document.getElementById('performanceSummary');
const navigationStatus = document.getElementById('navigationStatus');

let currentQuiz = {
    category: null,
    difficulty: null,
    questions: [],
    currentQuestion: 0,
    answers: [],
    timingData: [],
    startTime: null,
    questionStartTime: null,
    timerInterval: null,
    timeLeft: QUESTION_TIME_LIMIT
};

function setStatus(state, label) {
    if (!statusPill) {
        return;
    }
    statusPill.textContent = label;
    statusPill.classList.remove('status-pill--ready', 'status-pill--running', 'status-pill--complete');
    statusPill.classList.add(`status-pill--${state}`);
}

function showFeedback(message = '', type = 'info') {
    if (!feedbackBanner) {
        return;
    }
    feedbackBanner.classList.remove('feedback-banner--error', 'feedback-banner--success', 'feedback-banner--info');
    if (!message) {
        feedbackBanner.style.display = 'none';
        return;
    }
    feedbackBanner.textContent = message;
    feedbackBanner.classList.add(`feedback-banner--${type}`);
    feedbackBanner.style.display = 'block';
}

function clearNavigationStatus() {
    if (!navigationStatus) {
        return;
    }
    navigationStatus.textContent = '';
    navigationStatus.classList.remove('nav-status--answered', 'nav-status--unanswered');
    navigationStatus.style.visibility = 'hidden';
}

function updateNavigationStatus(questionIndex, isAnswered) {
    if (!navigationStatus || questionIndex == null) {
        return;
    }
    const questionLabel = `Question ${questionIndex + 1}`;
    navigationStatus.textContent = isAnswered ? `Previous question (${questionLabel}) answered.` : `Previous question (${questionLabel}) not answered yet.`;
    navigationStatus.classList.toggle('nav-status--answered', isAnswered);
    navigationStatus.classList.toggle('nav-status--unanswered', !isAnswered);
    navigationStatus.style.visibility = 'visible';
}

function formatTime(totalSeconds) {
    const seconds = Math.max(0, Math.round(totalSeconds));
    const minutes = Math.floor(seconds / 60);
    const remaining = seconds % 60;
    if (minutes === 0) {
        return `${remaining}s`;
    }
    return `${minutes}m ${remaining.toString().padStart(2, '0')}s`;
}

function startQuiz() {
    const categorySelect = document.getElementById('categorySelect');
    const difficultySelect = document.getElementById('difficultySelect');
    const category = categorySelect.value;
    const difficulty = difficultySelect.value;

    if (!category || !difficulty) {
        showFeedback('Please select both track and difficulty to proceed.', 'error');
        return;
    }

    const questionSet = quizDatabase?.[category]?.[difficulty];
    if (!questionSet || questionSet.length === 0) {
        showFeedback('Selected track does not have questions yet. Please choose a different combination.', 'error');
        return;
    }

    showFeedback();

    currentQuiz = {
        category,
        difficulty,
        questions: questionSet,
        currentQuestion: 0,
        answers: new Array(questionSet.length).fill(null),
        timingData: new Array(questionSet.length).fill(0),
        startTime: Date.now(),
        questionStartTime: Date.now(),
        timerInterval: null,
        timeLeft: QUESTION_TIME_LIMIT
    };

    const selectedCategoryLabel = categorySelect.options[categorySelect.selectedIndex].text;
    const selectedDifficultyLabel = difficultySelect.options[difficultySelect.selectedIndex].text;
    document.getElementById('categoryBadge').textContent = selectedCategoryLabel;
    document.getElementById('difficultyBadge').textContent = selectedDifficultyLabel;

    setStatus('running', 'In Progress');
    if (performanceSummary) {
        performanceSummary.style.display = 'none';
    }
    showPage('quizPage');
    clearNavigationStatus();
    loadQuestion();
    startTimer();
}

function loadQuestion() {
    const question = currentQuiz.questions[currentQuiz.currentQuestion];
    currentQuiz.questionStartTime = Date.now();
    clearNavigationStatus();

    document.getElementById('questionText').textContent = question.question;
    document.getElementById('questionNumber').textContent = `Question ${currentQuiz.currentQuestion + 1} of ${currentQuiz.questions.length}`;

    const progressPercentage = ((currentQuiz.currentQuestion + 1) / currentQuiz.questions.length) * 100;
    document.getElementById('progressFill').style.width = `${progressPercentage}%`;

    const optionsContainer = document.getElementById('optionsContainer');
    optionsContainer.innerHTML = '';

    question.options.forEach((option, index) => {
        const optionDiv = document.createElement('div');
        optionDiv.className = 'option';
        if (currentQuiz.answers[currentQuiz.currentQuestion] === index) {
            optionDiv.classList.add('selected');
        }
        optionDiv.innerHTML = `
            <input type="radio" name="answer" value="${index}" id="option${currentQuiz.currentQuestion}_${index}" ${currentQuiz.answers[currentQuiz.currentQuestion] === index ? 'checked' : ''}>
            <label for="option${currentQuiz.currentQuestion}_${index}">${String.fromCharCode(65 + index)}) ${option}</label>
        `;
        optionDiv.addEventListener('click', () => selectAnswer(index));
        optionDiv.querySelector('input').addEventListener('change', () => selectAnswer(index));
        optionsContainer.appendChild(optionDiv);
    });

    updateNavigationButtons();
}

function selectAnswer(index) {
    currentQuiz.answers[currentQuiz.currentQuestion] = index;
    document.querySelectorAll('.option').forEach((option, i) => {
        option.classList.toggle('selected', i === index);
    });
}

function nextQuestion() {
    if (currentQuiz.currentQuestion >= currentQuiz.questions.length - 1) {
        return;
    }
    const previousIndex = currentQuiz.currentQuestion;
    const wasAnswered = currentQuiz.answers[previousIndex] !== null;
    recordQuestionTime();
    currentQuiz.currentQuestion += 1;
    loadQuestion();
    resetTimer();
    updateNavigationStatus(previousIndex, wasAnswered);
}

function previousQuestion() {
    if (currentQuiz.currentQuestion === 0) {
        return;
    }
    const previousIndex = currentQuiz.currentQuestion;
    const wasAnswered = currentQuiz.answers[previousIndex] !== null;
    recordQuestionTime();
    currentQuiz.currentQuestion -= 1;
    loadQuestion();
    resetTimer();
    updateNavigationStatus(previousIndex, wasAnswered);
}

function recordQuestionTime() {
    if (!currentQuiz.questionStartTime) {
        return;
    }
    const timeSpent = Math.max(0, Math.round((Date.now() - currentQuiz.questionStartTime) / 1000));
    currentQuiz.timingData[currentQuiz.currentQuestion] = timeSpent;
}

function updateNavigationButtons() {
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const submitBtn = document.getElementById('submitBtn');

    prevBtn.hidden = currentQuiz.currentQuestion === 0;
    const isLastQuestion = currentQuiz.currentQuestion === currentQuiz.questions.length - 1;
    nextBtn.hidden = isLastQuestion;
    submitBtn.hidden = !isLastQuestion;
}

function startTimer(initialTime = QUESTION_TIME_LIMIT) {
    clearInterval(currentQuiz.timerInterval);
    currentQuiz.timeLeft = initialTime;
    updateTimerVisuals(currentQuiz.timeLeft);

    currentQuiz.timerInterval = setInterval(() => {
        currentQuiz.timeLeft -= 1;
        updateTimerVisuals(currentQuiz.timeLeft);

        if (currentQuiz.timeLeft <= 0) {
            clearInterval(currentQuiz.timerInterval);
            handleTimeExpiry();
        }
    }, 1000);
}

function updateTimerVisuals(timeLeft) {
    if (!timerDisplay || !timerRing || !timerSection) {
        return;
    }
    const safeTime = Math.max(0, timeLeft);
    timerDisplay.textContent = safeTime.toString().padStart(2, '0');
    const progressRatio = safeTime / QUESTION_TIME_LIMIT;
    timerRing.style.setProperty('--progress', Math.max(0, Math.min(1, progressRatio)));
    timerSection.classList.toggle('timer-section--critical', safeTime <= Math.floor(QUESTION_TIME_LIMIT * 0.25));
}

function handleTimeExpiry() {
    recordQuestionTime();
    if (currentQuiz.currentQuestion < currentQuiz.questions.length - 1) {
        const previousIndex = currentQuiz.currentQuestion;
        const wasAnswered = currentQuiz.answers[previousIndex] !== null;
        currentQuiz.currentQuestion += 1;
        loadQuestion();
        startTimer();
        updateNavigationStatus(previousIndex, wasAnswered);
    } else {
        submitQuiz();
    }
}

function resetTimer() {
    startTimer();
}

function submitQuiz() {
    recordQuestionTime();
    clearInterval(currentQuiz.timerInterval);
    calculateResults();
    setStatus('complete', 'Completed');
    clearNavigationStatus();
    showPage('resultsPage');
}

function calculateResults() {
    let correctCount = 0;
    let totalTime = 0;

    currentQuiz.questions.forEach((question, index) => {
        if (currentQuiz.answers[index] === question.correct) {
            correctCount += 1;
        }
        totalTime += currentQuiz.timingData[index];
    });

    const score = Math.round((correctCount / currentQuiz.questions.length) * 100);
    const incorrectCount = currentQuiz.questions.length - correctCount;

    document.getElementById('scoreValue').textContent = `${score}%`;
    document.getElementById('correctCount').textContent = correctCount;
    document.getElementById('incorrectCount').textContent = incorrectCount;
    document.getElementById('totalCount').textContent = currentQuiz.questions.length;
    document.getElementById('totalTime').textContent = formatTime(totalTime);

    renderPerformanceSummary(score, correctCount, totalTime);
    generateCharts(correctCount, incorrectCount);
    generateDetailedBreakdown();
}

function renderPerformanceSummary(score, correctCount, totalTime) {
    if (!performanceSummary) {
        return;
    }
    const accuracyDescriptor = score >= 85 ? 'exceptional accuracy' : score >= 70 ? 'solid performance' : score >= 50 ? 'steady outcome' : 'growth opportunity';
    const pacingDescriptor = totalTime / currentQuiz.questions.length <= QUESTION_TIME_LIMIT * 0.5 ? 'decisive pacing' : 'considered pacing';
    performanceSummary.innerHTML = `You answered <strong>${correctCount}</strong> out of <strong>${currentQuiz.questions.length}</strong> prompts correctly in <strong>${formatTime(totalTime)}</strong>, demonstrating ${accuracyDescriptor} with ${pacingDescriptor}.`;
    performanceSummary.style.display = 'block';
}

function generateCharts(correctCount, incorrectCount) {
    const answerCtx = document.getElementById('answerChart').getContext('2d');
    const timeCtx = document.getElementById('timeChart').getContext('2d');

    if (answerChartInstance) {
        answerChartInstance.destroy();
    }
    if (timeChartInstance) {
        timeChartInstance.destroy();
    }

    answerChartInstance = new Chart(answerCtx, {
        type: 'doughnut',
        data: {
            labels: ['Correct', 'Incorrect'],
            datasets: [{
                data: [correctCount, incorrectCount],
                backgroundColor: ['#34d399', '#f87171'],
                borderWidth: 0
            }]
        },
        options: {
            responsive: true,
            cutout: '65%',
            plugins: {
                legend: {
                    position: 'bottom',
                    labels: {
                        color: '#0f172a',
                        usePointStyle: true
                    }
                }
            }
        }
    });

    const labels = currentQuiz.questions.map((_, idx) => `Q${idx + 1}`);
    timeChartInstance = new Chart(timeCtx, {
        type: 'bar',
        data: {
            labels,
            datasets: [{
                label: 'Time (seconds)',
                data: currentQuiz.timingData,
                backgroundColor: '#60a5fa',
                borderRadius: 8
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: false
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        color: '#475569'
                    },
                    grid: {
                        color: 'rgba(148, 163, 184, 0.25)'
                    }
                },
                x: {
                    ticks: {
                        color: '#475569'
                    },
                    grid: {
                        display: false
                    }
                }
            }
        }
    });
}

function generateDetailedBreakdown() {
    const breakdownList = document.getElementById('breakdownList');
    breakdownList.innerHTML = '';

    currentQuiz.questions.forEach((question, index) => {
        const isCorrect = currentQuiz.answers[index] === question.correct;
        const selectedAnswer = currentQuiz.answers[index] !== null ? question.options[currentQuiz.answers[index]] : 'Not answered';
        const correctAnswer = question.options[question.correct];
        const timeSpent = currentQuiz.timingData[index];

        const breakdownItem = document.createElement('div');
        breakdownItem.className = `breakdown-item ${isCorrect ? 'correct' : 'incorrect'}`;
        breakdownItem.innerHTML = `
            <div class="breakdown-header">
                <span class="question-label">Q${index + 1}: ${question.question}</span>
                <span class="result-badge">${isCorrect ? '✓ Correct' : '✗ Incorrect'}</span>
            </div>
            <div class="breakdown-details">
                <p><strong>Your Answer:</strong> ${selectedAnswer}</p>
                ${!isCorrect ? `<p><strong>Correct Answer:</strong> ${correctAnswer}</p>` : ''}
                <p><strong>Time Taken:</strong> ${formatTime(timeSpent)}</p>
            </div>
        `;
        breakdownList.appendChild(breakdownItem);
    });
}

function restartQuiz() {
    clearInterval(currentQuiz.timerInterval);
    currentQuiz = {
        category: null,
        difficulty: null,
        questions: [],
        currentQuestion: 0,
        answers: [],
        timingData: [],
        startTime: null,
        questionStartTime: null,
        timerInterval: null,
        timeLeft: QUESTION_TIME_LIMIT
    };
    document.getElementById('categorySelect').value = '';
    document.getElementById('difficultySelect').value = '';
    if (performanceSummary) {
        performanceSummary.style.display = 'none';
    }
    showFeedback('Select a track and difficulty to begin your next assessment.', 'info');
    setStatus('ready', 'Awaiting Start');
    clearNavigationStatus();
    updateTimerVisuals(QUESTION_TIME_LIMIT);
    showPage('landingPage');
}

function showPage(pageId) {
    document.querySelectorAll('.page').forEach(page => page.classList.remove('active'));
    document.getElementById(pageId).classList.add('active');
}

// Initialize UI state on load
updateTimerVisuals(QUESTION_TIME_LIMIT);
showFeedback('Select a track and difficulty to begin your next assessment.', 'info');
