const quizDatabase = {
    ai: {
        easy: [
            {
                question: "What does AI stand for?",
                options: ["Automated Intelligence", "Artificial Intelligence", "Augmented Innovation", "Autonomous Interaction"],
                correct: 1
            },
            {
                question: "Which of the following is an example of narrow AI?",
                options: ["A self-driving car that handles every scenario", "Voice assistants like Alexa", "Human-level reasoning in all domains", "A general problem-solving agent"],
                correct: 1
            },
            {
                question: "Which programming language is widely used for AI prototyping?",
                options: ["Python", "Cobol", "PHP", "HTML"],
                correct: 0
            },
            {
                question: "What is the role of a training dataset in machine learning?",
                options: ["To store production logs", "To evaluate final models only", "To teach the model patterns", "To deploy models"],
                correct: 2
            },
            {
                question: "Which company developed the AlphaGo system that defeated a Go champion?",
                options: ["OpenAI", "DeepMind", "IBM", "Amazon"],
                correct: 1
            }
        ],
        medium: [
            {
                question: "Which algorithm is commonly used for supervised classification tasks?",
                options: ["K-means clustering", "Linear regression", "Support Vector Machines", "Apriori"],
                correct: 2
            },
            {
                question: "What does the term 'feature engineering' refer to?",
                options: ["Training deeper neural networks", "Selecting and crafting input variables", "Labeling datasets", "Deploying models"],
                correct: 1
            },
            {
                question: "Which activation function helps mitigate vanishing gradients in deep networks?",
                options: ["ReLU", "Sigmoid", "Softmax", "Linear"],
                correct: 0
            },
            {
                question: "What is the purpose of cross-validation?",
                options: ["To shuffle training labels", "To prevent overfitting through robust evaluation", "To speed up gradient descent", "To compress model weights"],
                correct: 1
            },
            {
                question: "Which evaluation metric is most informative for imbalanced classification problems?",
                options: ["Accuracy", "Mean squared error", "F1-score", "R-squared"],
                correct: 2
            }
        ],
        hard: [
            {
                question: "What does the vanishing gradient problem primarily impact?",
                options: ["Shallow decision trees", "Deep neural networks during training", "K-nearest neighbors", "Reinforcement learning policies"],
                correct: 1
            },
            {
                question: "In transformer architectures, what enables parallel processing of sequence data?",
                options: ["Recurrent connections", "Self-attention mechanism", "Pooling layers", "Dropout regularization"],
                correct: 1
            },
            {
                question: "Which technique stabilizes training in GANs?",
                options: ["Batch normalization in both generator and discriminator", "Using very large learning rates", "Removing noise from inputs", "Disabling backpropagation in the discriminator"],
                correct: 0
            },
            {
                question: "The KL divergence term in variational autoencoders encourages what behavior?",
                options: ["Deterministic encodings", "Latent distributions close to a prior", "Larger reconstruction errors", "Non-linear activations"],
                correct: 1
            },
            {
                question: "What is a key advantage of using federated learning?",
                options: ["Models are guaranteed to converge faster", "Data privacy is preserved by keeping data on-device", "Training requires no communication", "It eliminates the need for encryption"],
                correct: 1
            }
        ]
    },
    general: {
        easy: [
            {
                question: "What is the capital of France?",
                options: ["London", "Berlin", "Paris", "Madrid"],
                correct: 2
            },
            {
                question: "Which planet is closest to the sun?",
                options: ["Venus", "Mercury", "Earth", "Mars"],
                correct: 1
            },
            {
                question: "What is 2 + 2?",
                options: ["3", "4", "5", "6"],
                correct: 1
            },
            {
                question: "Which ocean is the largest?",
                options: ["Atlantic", "Indian", "Pacific", "Arctic"],
                correct: 2
            },
            {
                question: "What color is the sky on a clear day?",
                options: ["Red", "Green", "Blue", "Yellow"],
                correct: 2
            }
        ],
        medium: [
            {
                question: "Who wrote 'Romeo and Juliet'?",
                options: ["Mark Twain", "William Shakespeare", "Jane Austen", "Charles Dickens"],
                correct: 1
            },
            {
                question: "What is the chemical symbol for Gold?",
                options: ["Go", "Gd", "Au", "Ag"],
                correct: 2
            },
            {
                question: "In which year did World War II end?",
                options: ["1943", "1944", "1945", "1946"],
                correct: 2
            },
            {
                question: "What is the largest country by area?",
                options: ["Canada", "Russia", "United States", "China"],
                correct: 1
            },
            {
                question: "How many strings does a violin have?",
                options: ["4", "5", "6", "7"],
                correct: 0
            }
        ],
        hard: [
            {
                question: "Which philosopher proposed the concept of 'categorical imperative'?",
                options: ["Nietzsche", "Kant", "Hegel", "Descartes"],
                correct: 1
            },
            {
                question: "What is the smallest prime number greater than 100?",
                options: ["101", "103", "107", "109"],
                correct: 0
            },
            {
                question: "Which element has atomic number 79?",
                options: ["Silver", "Gold", "Platinum", "Palladium"],
                correct: 1
            },
            {
                question: "Who discovered penicillin?",
                options: ["Louis Pasteur", "Alexander Fleming", "Joseph Lister", "Edward Jenner"],
                correct: 1
            },
            {
                question: "What is the capital of Kazakhstan?",
                options: ["Almaty", "Astana", "Karaganda", "Shymkent"],
                correct: 1
            }
        ]
    },
    science: {
        easy: [
            {
                question: "What do plants need to survive?",
                options: ["Only water", "Water, sunlight, and soil", "Only air", "Only soil"],
                correct: 1
            },
            {
                question: "How many bones are in the human body?",
                options: ["186", "206", "226", "246"],
                correct: 1
            },
            {
                question: "What is the SI unit of force?",
                options: ["Joule", "Newton", "Pascal", "Watt"],
                correct: 1
            },
            {
                question: "Which gas do plants absorb from the atmosphere?",
                options: ["Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"],
                correct: 2
            },
            {
                question: "What is the freezing point of water?",
                options: ["0°C", "32°F", "273K", "All of the above"],
                correct: 3
            }
        ],
        medium: [
            {
                question: "What is the process by which plants make their own food?",
                options: ["Respiration", "Photosynthesis", "Fermentation", "Digestion"],
                correct: 1
            },
            {
                question: "Which vitamin is produced by the skin when exposed to sunlight?",
                options: ["Vitamin A", "Vitamin B", "Vitamin C", "Vitamin D"],
                correct: 3
            },
            {
                question: "What is the speed of light in vacuum?",
                options: ["3 × 10^8 m/s", "3 × 10^7 m/s", "3 × 10^9 m/s", "3 × 10^6 m/s"],
                correct: 0
            },
            {
                question: "Which organelle is the powerhouse of the cell?",
                options: ["Nucleus", "Ribosome", "Mitochondria", "Chloroplast"],
                correct: 2
            },
            {
                question: "What is the molecular formula of table salt?",
                options: ["NaCl", "NaClO", "Na2Cl", "NaCl2"],
                correct: 0
            }
        ],
        hard: [
            {
                question: "What is Avogadro's number?",
                options: ["6.02 × 10^22", "6.02 × 10^23", "6.02 × 10^24", "6.02 × 10^25"],
                correct: 1
            },
            {
                question: "What is the name of the protein that carries oxygen in blood?",
                options: ["Myoglobin", "Hemoglobin", "Chlorophyll", "Keratin"],
                correct: 1
            },
            {
                question: "Which law states that pressure and volume are inversely proportional?",
                options: ["Charles's Law", "Boyle's Law", "Gay-Lussac's Law", "Ideal Gas Law"],
                correct: 1
            },
            {
                question: "What is the half-life of Carbon-14?",
                options: ["5,730 years", "7,530 years", "3,730 years", "9,730 years"],
                correct: 0
            },
            {
                question: "Which particle was discovered by J.J. Thomson?",
                options: ["Proton", "Neutron", "Electron", "Photon"],
                correct: 2
            }
        ]
    },
    history: {
        easy: [
            {
                question: "In which year did Columbus discover America?",
                options: ["1491", "1492", "1493", "1494"],
                correct: 1
            },
            {
                question: "Who was the first President of the United States?",
                options: ["Thomas Jefferson", "George Washington", "John Adams", "Benjamin Franklin"],
                correct: 1
            },
            {
                question: "Which ancient wonder is the Great Pyramid part of?",
                options: ["Seven Wonders of the World", "Eight Wonders", "Five Wonders", "Ten Wonders"],
                correct: 0
            },
            {
                question: "What year did the Titanic sink?",
                options: ["1911", "1912", "1913", "1914"],
                correct: 1
            },
            {
                question: "Who invented the printing press?",
                options: ["Leonardo da Vinci", "Michelangelo", "Johannes Gutenberg", "Galileo"],
                correct: 2
            }
        ],
        medium: [
            {
                question: "Which empire built Machu Picchu?",
                options: ["Aztec", "Maya", "Inca", "Toltec"],
                correct: 2
            },
            {
                question: "In which year did the Berlin Wall fall?",
                options: ["1987", "1988", "1989", "1990"],
                correct: 2
            },
            {
                question: "Who was the first Emperor of Rome?",
                options: ["Julius Caesar", "Augustus", "Nero", "Tiberius"],
                correct: 1
            },
            {
                question: "Which treaty ended World War I?",
                options: ["Treaty of Versailles", "Treaty of Brest-Litovsk", "Treaty of Trianon", "Treaty of Neuilly"],
                correct: 0
            },
            {
                question: "In which century did the Renaissance occur?",
                options: ["12th", "13th", "14th", "15th"],
                correct: 3
            }
        ],
        hard: [
            {
                question: "Who was the primary architect of the Indian Constitution?",
                options: ["Jawaharlal Nehru", "B.R. Ambedkar", "Sardar Patel", "Mahatma Gandhi"],
                correct: 1
            },
            {
                question: "In which year did the Russian Revolution occur?",
                options: ["1916", "1917", "1918", "1919"],
                correct: 1
            },
            {
                question: "Which dynasty built the Great Wall of China?",
                options: ["Shang", "Tang", "Ming", "Qin"],
                correct: 3
            },
            {
                question: "Who was the first President of South Africa after apartheid?",
                options: ["Thabo Mbeki", "Nelson Mandela", "F.W. de Klerk", "Jacob Zuma"],
                correct: 1
            },
            {
                question: "In which year did the American Civil War begin?",
                options: ["1859", "1860", "1861", "1862"],
                correct: 2
            }
        ]
    },
    geography: {
        easy: [
            {
                question: "What is the capital of Japan?",
                options: ["Osaka", "Tokyo", "Kyoto", "Hiroshima"],
                correct: 1
            },
            {
                question: "Which continent is the largest?",
                options: ["Africa", "Europe", "Asia", "Antarctica"],
                correct: 2
            },
            {
                question: "What is the longest river in the world?",
                options: ["Amazon", "Nile", "Yangtze", "Mississippi"],
                correct: 1
            },
            {
                question: "How many continents are there?",
                options: ["5", "6", "7", "8"],
                correct: 2
            },
            {
                question: "Which country has the most population?",
                options: ["India", "Indonesia", "China", "United States"],
                correct: 2
            }
        ],
        medium: [
            {
                question: "What is the capital of Brazil?",
                options: ["Rio de Janeiro", "São Paulo", "Brasília", "Salvador"],
                correct: 2
            },
            {
                question: "Which mountain range has the highest peak?",
                options: ["Rocky Mountains", "Andes", "Himalayas", "Alps"],
                correct: 2
            },
            {
                question: "What is the capital of Australia?",
                options: ["Sydney", "Melbourne", "Canberra", "Brisbane"],
                correct: 2
            },
            {
                question: "Which desert is the largest in the world?",
                options: ["Sahara", "Arabian", "Gobi", "Antarctic"],
                correct: 3
            },
            {
                question: "What is the smallest country by area?",
                options: ["Monaco", "San Marino", "Vatican City", "Liechtenstein"],
                correct: 2
            }
        ],
        hard: [
            {
                question: "What is the capital of Papua New Guinea?",
                options: ["Port Moresby", "Lae", "Madang", "Goroka"],
                correct: 0
            },
            {
                question: "Which country has the most time zones?",
                options: ["Russia", "France", "USA", "China"],
                correct: 1
            },
            {
                question: "What is the deepest ocean trench?",
                options: ["Tonga Trench", "Mariana Trench", "Japan Trench", "Kuril Trench"],
                correct: 1
            },
            {
                question: "Which African country was formerly called Rhodesia?",
                options: ["Zambia", "Zimbabwe", "Malawi", "Botswana"],
                correct: 1
            },
            {
                question: "What is the capital of Tajikistan?",
                options: ["Khujand", "Dushanbe", "Kulob", "Khorog"],
                correct: 1
            }
        ]
    }
};
